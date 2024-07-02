package com.AuthJWT.authJWT.services;

import com.AuthJWT.authJWT.entities.User;

import java.util.List;

public interface UserService {
    List<User> findAll();

    User save(User user);
}
