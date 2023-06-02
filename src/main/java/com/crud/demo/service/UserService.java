package com.crud.demo.service;

import com.crud.demo.model.UserModel;
import com.crud.demo.model.UserRequestModel;
import com.crud.demo.repositoy.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserModel> getAllUsers() {
        return userRepository.findAll();
    }

    public UserModel getUserById(Long id) {
        Optional<UserModel> optionalUser = userRepository.findById(id);
        return optionalUser.orElse(null);
    }

    public UserModel createUser(UserRequestModel userRequestModel) {
        UserModel user = new UserModel();
        user.setNombre(userRequestModel.getNombre());
        user.setApellido(userRequestModel.getApellido());
        user.setCorreoElectronico(userRequestModel.getCorreoElectronico());
        user.setFechaNacimiento(parseDate(userRequestModel.getFechaNacimiento()));
        return userRepository.save(user);
    }

    public UserModel updateUser(Long id, UserRequestModel user) {
        Optional<UserModel> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            UserModel existingUser = optionalUser.get();
            existingUser.setNombre(user.getNombre());
            existingUser.setApellido(user.getApellido());
            existingUser.setCorreoElectronico(user.getCorreoElectronico());
            existingUser.setFechaNacimiento(parseDate(user.getFechaNacimiento()));
            return userRepository.save(existingUser);
        } else {
            return null;
        }
    }

    public boolean deleteUser(Long id) {
        Optional<UserModel> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            userRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    // Convertir la fecha de nacimiento de String a LocalDate
    public LocalDate parseDate(String birthDay) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-M-yyyy");
            LocalDate parsed = LocalDate.parse(birthDay, formatter);
            return parsed;
        } catch (DateTimeParseException e) {
            throw new RuntimeException(e.getCause());
        }
    }
}
