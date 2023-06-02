package com.crud.demo.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Objeto de solicitud para crear un usuario")
public class UserRequestModel {
    @ApiModelProperty(value = "Nombre del usuario", example = "Hector")
    private String nombre;

    @ApiModelProperty(value = "Apellido del usuario", example = "Munguia")
    private String apellido;

    @ApiModelProperty(value = "Correo electrónico del usuario", example = "hector@example.com")
    private String correoElectronico;

    @ApiModelProperty(value = "Fecha de nacimiento del usuario en formato dd-m-yyyy", example = "30-04-2001")
    private String fechaNacimiento;

    // Getters y Setters

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
}

