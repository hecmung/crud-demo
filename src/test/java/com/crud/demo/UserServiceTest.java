package com.crud.demo;

import com.crud.demo.model.UserModel;
import com.crud.demo.model.UserRequestModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;

// Permite ejecutar las pruebas con las anotaciones de Mockito
@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    // Objetos para las pruebas
    private UserModel user1;
    private UserModel user2;
    private UserRequestModel createUserRequest1;
    private UserRequestModel createUserRequest2;

    // Se ejecuta el llenado de los objetos antes de cada prueba
    @BeforeEach
    public void setup() {
        user1 = new UserModel(1L, "John", "Doe", "john@example.com", LocalDate.of(1990, 1, 1));
        user2 = new UserModel(2L, "Jane", "Smith", "jane@example.com", LocalDate.of(1995, 2, 2));

        createUserRequest1 = new UserRequestModel();
        createUserRequest1.setNombre("John");
        createUserRequest1.setApellido("Doe");
        createUserRequest1.setCorreoElectronico("john@example.com");
        createUserRequest1.setFechaNacimiento("1990-01-01");

        createUserRequest2 = new UserRequestModel();
        createUserRequest2.setNombre("Jane");
        createUserRequest2.setApellido("Smith");
        createUserRequest2.setCorreoElectronico("jane@example.com");
        createUserRequest2.setFechaNacimiento("1995-02-02");
    }

}
