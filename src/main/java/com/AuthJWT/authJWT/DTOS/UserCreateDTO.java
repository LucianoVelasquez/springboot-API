package com.AuthJWT.authJWT.DTOS;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class UserCreateDTO
{
    @NotEmpty
    @Size(min = 3, max = 20)
    public String username;

    @NotEmpty
    @Size(min = 8)
    public String password;

    public UserCreateDTO() {
    }

    public UserCreateDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
