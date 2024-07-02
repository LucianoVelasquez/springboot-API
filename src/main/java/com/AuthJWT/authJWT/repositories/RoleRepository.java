package com.AuthJWT.authJWT.repositories;

import com.AuthJWT.authJWT.entities.Role;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RoleRepository extends CrudRepository<Role,Long> {

    Optional<Role> findByName(String name);

}
