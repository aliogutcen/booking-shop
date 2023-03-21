package com.ogutcenali.service;

import com.ogutcenali.dto.request.CreateAuthRequestDto;
import com.ogutcenali.dto.request.LoginRequestDto;
import com.ogutcenali.exception.AuthException;
import com.ogutcenali.exception.EErrorType;
import com.ogutcenali.mapper.IAuthMapper;
import com.ogutcenali.rabbitmq.model.ActivateStatus;
import com.ogutcenali.rabbitmq.model.CreateUserProfileFromAuth;
import com.ogutcenali.rabbitmq.producer.AuthProducer;
import com.ogutcenali.repository.IAuthRepository;
import com.ogutcenali.repository.entity.Auth;
import com.ogutcenali.repository.entity.Roles;
import com.ogutcenali.repository.enums.EStatus;
import com.ogutcenali.utility.JwtTokenManager;
import com.ogutcenali.utility.PasswordEncrypt;
import com.ogutcenali.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService extends ServiceManager<Auth, Long> {
    private final IAuthRepository authRepository;
    private final JwtTokenManager jwtTokenManager;
    private final AuthProducer authProducer;
    private final PasswordEncrypt passwordEncrypt;
    private final RoleService roleService;

    public AuthService(IAuthRepository authRepository, JwtTokenManager jwtTokenManager, AuthProducer authProducer, PasswordEncrypt passwordEncrypt, RoleService roleService) {
        super(authRepository);
        this.authRepository = authRepository;
        this.jwtTokenManager = jwtTokenManager;
        this.authProducer = authProducer;
        this.passwordEncrypt = passwordEncrypt;
        this.roleService = roleService;
    }

    public Boolean createAuth(CreateAuthRequestDto createAuthRequestDto) {
        if (!createAuthRequestDto.getPassword().equals(createAuthRequestDto.getRepassword()))
            throw new AuthException(EErrorType.AUTH_PASSWORD_ERROR);
        String pass = passwordEncrypt.generateEncryptPassword(createAuthRequestDto.getPassword());
        Auth auth = IAuthMapper.INSTANCE.toCreateAuth(createAuthRequestDto);
        auth.setPassword(pass);
        save(auth);
        Roles roles = roleService.addRoleForNewAuth(auth.getId());
        authProducer.createUserProfile(CreateUserProfileFromAuth.builder()
                .authid(auth.getId())
                .mail(auth.getMail())
                .username(auth.getUsername())
                .role(roles.getErole())
                .estatus(auth.getEstatus())
                .build());
        return true;
    }

    public String doLogin(LoginRequestDto loginRequestDto) {
        String pass = passwordEncrypt.generateEncryptPassword(loginRequestDto.getPassword());
        Optional<Auth> auth = authRepository.findOptionalByUsernameAndPassword(loginRequestDto.getUsername(), pass);
        if (auth.isEmpty()) throw new AuthException(EErrorType.AUTH_LOGIN_ERROR);
        Optional<String> token = jwtTokenManager.createToken(auth.get().getId());
        return token.get();
    }

    public Boolean activateAuth(Long id) {
        Optional<Auth> authOptional = findById(id);
        if (authOptional.isEmpty()) throw new AuthException(EErrorType.AUTH_NOT_FOUND_ID);
        Auth auth = authOptional.get();
        auth.setEstatus(EStatus.ACTIVE);
        update(auth);
        authProducer.activateStatus(ActivateStatus.builder()
                .authid(auth.getId())
                .estatus(auth.getEstatus())
                .build());
        return true;
    }
}
