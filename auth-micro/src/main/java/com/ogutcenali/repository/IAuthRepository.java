package com.ogutcenali.repository;

import com.ogutcenali.repository.entity.Auth;
import com.ogutcenali.repository.enums.EStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IAuthRepository extends JpaRepository<Auth, Long> {

    Optional<Auth> findOptionalByUsernameAndPassword(String username, String password);
    Boolean findByEstatusAndUsername(EStatus eStatus,String username);
}
