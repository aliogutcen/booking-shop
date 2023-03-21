package com.ogutcenali.repository;

import com.ogutcenali.repository.entity.UserRoles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IUserRolesRepository extends JpaRepository<UserRoles,Long> {

    List<UserRoles> findByUserid(Long userid);

    Optional<UserRoles> findOptionalByUserid(Long id);
}
