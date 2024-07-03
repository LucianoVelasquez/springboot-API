package com.AuthJWT.authJWT.controllers;


import com.AuthJWT.authJWT.DTOS.UserCreateDTO;
import com.AuthJWT.authJWT.entities.User;
import com.AuthJWT.authJWT.services.UserServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
@Tag(name = "user", description = "the user API")
public class UserController {

    @Autowired
    private UserServiceImpl service;

    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping
    @Operation(summary = "Get All User", description = "This can only be done by the logged in user.",tags = { "user" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get all user",
                    content = @Content(mediaType = "application/json",schema = @Schema(implementation = User.class))),
            @ApiResponse(responseCode = "400", description = "not found",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "401", description = "token invalid",
                    content = @Content(mediaType = "application/json"))
    })
    public ResponseEntity<?> list(){
        return ResponseEntity.ok().body(service.findAll());
    }

    @PostMapping
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "create User",
                    content = @Content(mediaType = "application/json",schema = @Schema(implementation = UserCreateDTO.class))),
            @ApiResponse(responseCode = "400", description = "body incorrect",
                    content = @Content(mediaType = "application/json",schema = @Schema(implementation = String.class)))
    })
    @Operation(summary = "Create user", description = "This can be used by everyone, * The username must be between 3 and 20 characters " +
            " * Password must be at least 8 characters", tags = { "user" })
    public ResponseEntity<?> create(@Valid @RequestBody UserCreateDTO userDTO, BindingResult result){

        if(result.hasFieldErrors()){
            return validation(result);
        }

        User newUser = new User(userDTO.getUsername(), userDTO.getPassword());

        service.save(newUser);

        return ResponseEntity.status(HttpStatus.CREATED).body(userDTO);
    }

    private ResponseEntity<?> validation(BindingResult result) {
        Map<String,String> errors = new HashMap<>();

        result.getFieldErrors().forEach(err ->{
            errors.put(err.getField(),"El campo "+ err.getField() + " " + err.getDefaultMessage());
        });

        return ResponseEntity.badRequest().body(errors);
    }


}
