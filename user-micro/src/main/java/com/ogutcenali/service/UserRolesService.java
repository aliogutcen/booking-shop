package com.ogutcenali.service;

import com.ogutcenali.exception.EErrorType;
import com.ogutcenali.exception.UserProfileException;
import com.ogutcenali.repository.IUserRolesRepository;
import com.ogutcenali.repository.entity.UserRoles;
import com.ogutcenali.repository.enums.ERole;
import com.ogutcenali.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserRolesService extends ServiceManager<UserRoles, Long> {
    private final IUserRolesRepository userRolesRepository;

    public UserRolesService(IUserRolesRepository userRolesRepository) {
        super(userRolesRepository);
        this.userRolesRepository = userRolesRepository;
    }
    public void addRole(Long id, ERole role) {
        UserRoles userRoles = UserRoles.builder()
                .userid(id)
                .erole(role).build();
        save(userRoles);
    }
    public List<UserRoles> userRolesGetUserId(Long userid) {
        return userRolesRepository.findByUserid(userid);
    }
    public void deleteUserRoleById(Long userId) {
        Optional<UserRoles> userRoles = userRolesRepository.findOptionalByUserid(userId);
        if (userRoles.isEmpty()) throw new UserProfileException(EErrorType.USER_NOT_FOUND);
        delete(userRoles.get());
    }
}

