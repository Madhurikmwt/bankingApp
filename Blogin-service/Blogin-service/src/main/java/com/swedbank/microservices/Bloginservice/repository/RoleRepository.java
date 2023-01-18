package com.swedbank.microservices.Bloginservice.repository;

import com.swedbank.microservices.Bloginservice.constants.ERole;
import com.swedbank.microservices.Bloginservice.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
