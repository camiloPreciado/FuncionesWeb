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
 * @since 1.0
 * @version 1.2.1
 */
public class EstudianteDto implements Serializable{
    
    /**
     * Almacena la cedula del estudiante
     */
    @NotNull(message = "El campo no puede estar vacio") //Condicion NotNull variable cedula
    @Size(min = 10, max = 10) //Condicion Size variable cedula
    private String cedula;
    
    /**
     * Almacena el nombre del estudiante
     */
    @NotNull //Condicion NotNull variable nombre
    @Size(min = 3) //Condicion Size variable nombre
    private String nombre;
    
    /**
     * Almacena el apellido del estudiante
     */    
    @NotNull //Condicion NotNull variable apellido
    @Size(min = 5) //Condicion Size variable apellido
    private String apellido;
    
    /**
     * Almacena la edad del estudiante
     */
    @NotNull //Condicion NotNull variable edad
    @Min(value = 18, message = "La edad no puede ser menor a 18")//Condicion Min variable edad
    private Integer edad;
    
    /**
     * Almacena el correo del estudiante
     */
    @NotNull //Condicion NotNull variable correo
    @Email //Condicion Email variable correo
    private String correo;
    
    /**
     * Almacena Lista de materias del estudiante
     */
    @NotNull //Condicion NotNull variable listaMateria
    private List<String> listaMateria;
    
    /**
     * Almacena numeros del estudiante
     */
    @NotNull //Condicion NotNull variable numero
    private int[] numero;
    
    /**
     *Constructor de la clase vacio
     */
    public EstudianteDto(){
    
    }
    
    /**
     *Constructor de la clase
     */
    public EstudianteDto(String cedula, String nombre, String apellido, Integer edad, String correo, List<String> listaMateria, int[] numero) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.correo = correo;
        this.listaMateria = listaMateria;
        this.numero = numero;
    }

    
    /**
     * Metodos set y get de las variables cedula, nombre, apellido, edad, correo, listaMateria, numero
     */
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
