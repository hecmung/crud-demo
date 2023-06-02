package com.crud.demo.controller;

import com.crud.demo.model.UserModel;
import com.crud.demo.model.UserRequestModel;
import com.crud.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Funcion para crear usuario
    @PostMapping("/create")
    public ResponseEntity<UserModel> createUser(@RequestBody UserRequestModel userRequestModel) {
        UserModel createdUser;

        try {
            createdUser = userService.createUser(userRequestModel);
            return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
        } catch (HttpServerErrorException httpServerErrorException) {
            httpServerErrorException.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    // Funcion para obtener todos los usuarios
    @GetMapping("/get-all")
    public ResponseEntity<List<UserModel>> getAllUsers() {
        List<UserModel> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    // Funcion para obtener un usuario, lo busca por el id
    @GetMapping("/get/{id}")
    public ResponseEntity<UserModel> getUserById(@PathVariable("id") Long id) {
        UserModel user = userService.getUserById(id);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Funcion para actualizar un usuario, lo busca por el id
    @PutMapping("/update/{id}")
    public ResponseEntity<UserModel> updateUser(@PathVariable("id") Long id, @RequestBody UserRequestModel user) {
        UserModel updatedUser;

        try {
            updatedUser = userService.updateUser(id, user);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        if (updatedUser != null) {
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Funcion para eliminar un usuario, lo busca por el id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") Long id) {
        boolean deleted = userService.deleteUser(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
