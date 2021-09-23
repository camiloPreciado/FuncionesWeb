/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udec.funcionesweb.Dto;

import java.io.Serializable;
import java.util.List;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Camilo Preciado
 */
public class EstudianteDto implements Serializable{
    
    @NotNull(message = "El campo no puede estar vacio")
    @Size(max = 10)
    private String cedula;
    
    @NotNull
    @Size(min = 3)
    private String nombre;
    
    @NotNull
    @Size(min = 5)
    private String apellido;
    
    @Min(value = 18, message = "La edad no puede ser menor a 18")
    @NotNull
    private Integer edad;
    
    @NotNull
    @Email
    private String correo;
    
    @NotNull
    @Size(min = 1)
    private List<String> listaMateria;
    
    @NotNull
    private int[] numero;
    
    public EstudianteDto(){
    
    }

    public EstudianteDto(String cedula, String nombre, String apellido, Integer edad, String correo, List<String> listaMateria, int[] numero) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.correo = correo;
        this.listaMateria = listaMateria;
        this.numero = numero;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

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

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public List<String> getListaMateria() {
        return listaMateria;
    }

    public void setListaMateria(List<String> listaMateria) {
        this.listaMateria = listaMateria;
    }

    public int[] getNumero() {
        return numero;
    }

    public void setNumero(int[] numero) {
        this.numero = numero;
    }

    @Override
    public String toString() {
        return "EstudianteDto{" + "cedula=" + cedula + ", nombre=" + nombre + ", apellido=" + apellido + ", edad=" + edad + ", correo=" + correo + ", listaMateria=" + listaMateria + ", numero=" + numero + '}';
    }

    public boolean isEmpty() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
