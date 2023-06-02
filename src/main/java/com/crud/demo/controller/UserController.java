package com.crud.demo.controller;

import com.crud.demo.model.UserModel;
import com.crud.demo.model.UserRequestModel;
import com.crud.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserModel> createUser(@RequestBody UserRequestModel userRequestModel) {
        UserModel createdUser = new UserModel();

        try {
            createdUser = userService.createUser(userRequestModel);
        } catch (HttpServerErrorException httpServerErrorException) {
            httpServerErrorException.printStackTrace();
            return new ResponseEntity<>(createdUser, HttpStatus.INTERNAL_SERVER_ERROR);
        }catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(createdUser, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }
}
