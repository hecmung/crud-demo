package com.crud.demo;

import com.crud.demo.model.UserModel;
import com.crud.demo.model.UserRequestModel;
import com.crud.demo.repositoy.UserRepository;
import com.crud.demo.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

// Permite ejecutar las pruebas con las anotaciones de Mockito
@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    // Crea y simula el comportamiento del repositorio
    @Mock
    private UserRepository userRepository;

    // Inyección del servicio
    @InjectMocks
    private UserService userService;

    // Objetos para las pruebas
    private UserModel user1;
    private UserModel user2;
    private UserRequestModel createUserRequest1;
    private UserRequestModel createUserRequest2;

    // Se ejecuta el llenado de los objetos antes de cada prueba
    @Before
    public void setup() {
        user1 = new UserModel(1L, "Hector", "Munguia", "hector@example.com", LocalDate.parse("30-04-2001", DateTimeFormatter.ofPattern("dd-M-yyyy")));
        user2 = new UserModel(2L, "Adrian", "Aguilera", "adrian@example.com", LocalDate.parse("25-05-1999", DateTimeFormatter.ofPattern("dd-M-yyyy")));

        createUserRequest1 = new UserRequestModel();
        createUserRequest1.setNombre("Hector");
        createUserRequest1.setApellido("Munguia");
        createUserRequest1.setCorreoElectronico("hector@example.com");
        createUserRequest1.setFechaNacimiento("30-04-2001");

        createUserRequest2 = new UserRequestModel();
        createUserRequest2.setNombre("Adrian");
        createUserRequest2.setApellido("Aguilera");
        createUserRequest2.setCorreoElectronico("adrian@example.com");
        createUserRequest2.setFechaNacimiento("25-05-1999");
    }

    // Prueba para crear un usuario
    @Test
    public void testCreateUser() {
        // Cuando se ejecuta la funcion save del repository retorna un objeto user1
        when(userRepository.save(any(UserModel.class))).thenReturn(user1);

        // Simula la operación del controlador llamando al service para crear un usuario
        UserModel createdUser = userService.createUser(createUserRequest1);

        // Se verifica que el usuario creado sea igual al user1
        assertEquals(user1, createdUser);

        // Verifica que la funcion save fue utilizada
        verify(userRepository).save(any(UserModel.class));
    }

    @Test
    public void testGetUserById() {
        // Cuando se ejecuta la funcion findById del repository retorna un objeto user1
        when(userRepository.findById(1L)).thenReturn(java.util.Optional.ofNullable(user1));

        // Simula la operación del controlador llamando al service para buscar un usuario
        UserModel user = userService.getUserById(1L);

        // Se verifica que el usuario encontrado sea igual al user1
        assertEquals(user1, user);

        // Verifica que la funcion findById fue utilizada con el valor 1L
        verify(userRepository).findById(1L);
    }

    @Test
    public void testGetAllUsers() {
        // Crea el list de usuarios
        List<UserModel> userList = Arrays.asList(user1, user2);

        // Cuando se ejecuta la funcion findAll del repository retorna la lista de usuarios creada
        when(userRepository.findAll()).thenReturn(userList);

        // Simula la operación del controlador llamando al service para buscar todos los usuarios
        List<UserModel> users = userService.getAllUsers();

        // Se verifica que la lista de usuarios encontrados sea igual al usersList
        assertEquals(userList, users);

        // Verifica que la funcion findAll fue utilizada
        verify(userRepository).findAll();
    }
}
