package com.ogutcenali.service;

import com.ogutcenali.repository.IRolesRepository;
import com.ogutcenali.repository.entity.Roles;
import com.ogutcenali.repository.enums.ERole;
import com.ogutcenali.utility.ServiceManager;
import org.springframework.stereotype.Service;

@Service
public class RoleService extends ServiceManager<Roles, Long> {

    private final IRolesRepository rolesRepository;

    public RoleService(IRolesRepository rolesRepository) {
        super(rolesRepository);
        this.rolesRepository = rolesRepository;
    }
    public Roles addRoleForNewAuth(Long id) {
        if (id != 1) {
            return save(Roles.builder()
                    .erole(ERole.USER)
                    .authid(id)
                    .build());
        }

        return save(Roles.builder()
                .erole(ERole.ADMIN)
                .authid(id)
                .build());
    }
    public ERole getRolesNameForByAuthId(Long authid) {
        return rolesRepository.findByAuthid(authid);
    }
}
