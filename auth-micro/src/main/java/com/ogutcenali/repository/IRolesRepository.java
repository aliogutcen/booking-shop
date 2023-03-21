package com.ogutcenali.repository;

import com.ogutcenali.repository.entity.Roles;
import com.ogutcenali.repository.enums.ERole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IRolesRepository extends JpaRepository<Roles, Long> {

    ERole findByAuthid(Long authid);
}
