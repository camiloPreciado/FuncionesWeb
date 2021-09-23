/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udec.funcionesweb.Service;

import com.udec.funcionesweb.Dto.EstudianteDto;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Camilo Preciado
 * @since 1.0
 * @version 1.5.1
 */
public class EstudianteService {
    
    /**
     * Almacena la lista de estudiantes
     */
    private List<EstudianteDto> listaEstudiantes;
    
    /**
     * Almacena los datos del estudiante
     */
    private EstudianteDto estudiante = new EstudianteDto();
    
    /**
     * Almacena la existencia del estudiante
     */
    private boolean existe;
    
    /**
     *Constructor de la clase
     */
    public EstudianteService() {
    }
    
    /**
     * Obtiene la lista de estudiantes en el archivo plano
     */   
    public List<EstudianteDto> obtenerLista(){
        FileInputStream ficheroL = null;    
        try {
            ficheroL = new FileInputStream("datos.txt");
            ObjectInputStream conexionF = new ObjectInputStream(ficheroL);
            listaEstudiantes = (List<EstudianteDto>)conexionF.readObject(); 
        }catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }catch  (IOException ex){
            ex.printStackTrace();
        }catch (ClassNotFoundException ex){
            ex.printStackTrace();
        }                 
        return listaEstudiantes;
    }
    
//----------------------------------------------------------------------------------
    
    /**
     * Obtiene un estudiante en concreto del archivo plano
     */   
    public EstudianteDto obtenerPorCedula(String cedula){
        int posicion = 0;
        boolean existe = false;
        FileInputStream ficheroL = null;    
        try {
            ficheroL = new FileInputStream("datos.txt");
            ObjectInputStream conexionF = new ObjectInputStream(ficheroL);
            listaEstudiantes = (List<EstudianteDto>)conexionF.readObject();

        }catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }catch  (IOException ex){
            ex.printStackTrace();
        }catch (ClassNotFoundException ex){
            ex.printStackTrace();
        }
        for (int i=0; i<listaEstudiantes.size(); i++) {
           if(cedula.equalsIgnoreCase(listaEstudiantes.get(i).getCedula())){
               existe = true;
               posicion = i;
           }
        }
        if(existe == true){
            estudiante = listaEstudiantes.get(posicion);
        }
        return estudiante;
    }
    
//----------------------------------------------------------------------------------------
    
    /**
     * Agrega nuevo estudiante en el archivo plano
     */   
    public boolean agregar(EstudianteDto estudiante){
         existe = false;
         FileInputStream ficheroL = null;     
        try {
            ficheroL = new FileInputStream("datos.txt");
            ObjectInputStream conexionF = new ObjectInputStream(ficheroL);
            listaEstudiantes = (List<EstudianteDto>)conexionF.readObject();

        }catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }catch  (IOException ex){
            ex.printStackTrace();
        }catch (ClassNotFoundException ex){
            ex.printStackTrace();
        }
        
        for (EstudianteDto estudiantes : listaEstudiantes) {
            if(estudiante.getCedula().equalsIgnoreCase(estudiantes.getCedula())){
                existe = true;
            }
        }
        
        if(existe == false){
            listaEstudiantes.add(estudiante);
            FileOutputStream fichero = null;
            try {
                fichero = new FileOutputStream("datos.txt");
                ObjectOutputStream conexionF = new ObjectOutputStream(fichero);
                conexionF.writeObject(listaEstudiantes);
            }catch (FileNotFoundException ex) {
                ex.printStackTrace();            
            }catch  (IOException ex){
                ex.printStackTrace();
            }finally{
                try {
                    fichero.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }   
        }
        return existe;
    }
    
//-----------------------------------------------------------------------------------------   
    
    /**
     * Elimina un estudiante en concreto del archivo plano
     */   
    public boolean elminarPorCedula(String cedula){
        existe = false;
        int posicion = 0;
         FileInputStream ficheroL = null;     
        try {
            ficheroL = new FileInputStream("datos.txt");
            ObjectInputStream conexionF = new ObjectInputStream(ficheroL);
            listaEstudiantes = (List<EstudianteDto>)conexionF.readObject();

        }catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }catch  (IOException ex){
            ex.printStackTrace();
        }catch (ClassNotFoundException ex){
            ex.printStackTrace();
        }
         
        for (int i=0; i<listaEstudiantes.size(); i++) {
            if(cedula.equalsIgnoreCase(listaEstudiantes.get(i).getCedula())){
                existe = true;
                posicion = i;
            }
        }        
        if(existe == true){
            listaEstudiantes.remove(posicion);
            FileOutputStream fichero = null;
            try {
                fichero = new FileOutputStream("datos.txt");
                ObjectOutputStream conexionF = new ObjectOutputStream(fichero);
                conexionF.writeObject(listaEstudiantes);
            }catch (FileNotFoundException ex) {
                ex.printStackTrace();            
            }catch  (IOException ex){
                ex.printStackTrace();
            }finally{
                try {
                    fichero.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } 
        }
        return existe;
    }
    
//--------------------------------------------------------------------------------------------

    /**
     * Edita a un estudiante en concreto en el archivo plano
     */   
    public boolean editar(EstudianteDto estudiante){
        existe = false;
        int posicion = 0;
        List<EstudianteDto> listaEstudiantes = new ArrayList<EstudianteDto>();
         FileInputStream ficheroL = null;     
        try {
            ficheroL = new FileInputStream("datos.txt");
            ObjectInputStream conexionF = new ObjectInputStream(ficheroL);
            listaEstudiantes = (List<EstudianteDto>)conexionF.readObject();

        }catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }catch  (IOException ex){
            ex.printStackTrace();
        }catch (ClassNotFoundException ex){
            ex.printStackTrace();
        }
         
        for (int i=0; i<listaEstudiantes.size(); i++) {
            if(estudiante.getCedula().equalsIgnoreCase(listaEstudiantes.get(i).getCedula())){
                existe = true;
                posicion = i;
            }
        }
        
        if(existe == true){
            listaEstudiantes.get(posicion).setNombre(estudiante.getNombre());
            listaEstudiantes.get(posicion).setApellido(estudiante.getApellido());
            listaEstudiantes.get(posicion).setEdad(estudiante.getEdad());
            listaEstudiantes.get(posicion).setCorreo(estudiante.getCorreo());
            listaEstudiantes.get(posicion).setListaMateria(estudiante.getListaMateria());
            listaEstudiantes.get(posicion).setNumero(estudiante.getNumero());
            FileOutputStream fichero = null;
            try {
                fichero = new FileOutputStream("datos.txt");
                ObjectOutputStream conexionF = new ObjectOutputStream(fichero);
                conexionF.writeObject(listaEstudiantes);
            }catch (FileNotFoundException ex) {
                ex.printStackTrace();            
            }catch  (IOException ex){
                ex.printStackTrace();
            }finally{
                try {
                    fichero.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }  
        }
        return existe;
    }
    
    
    /**
     * Metodos set y get de las variables listaEstudinates, estudiante
     */
    public List<EstudianteDto> getListaEstudiantes() {
        return listaEstudiantes;
    }

    public void setListaEstudiantes(List<EstudianteDto> listaEstudiantes) {
        this.listaEstudiantes = listaEstudiantes;
    }

    public EstudianteDto getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(EstudianteDto estudiante) {
        this.estudiante = estudiante;
    }
    
    
    /**
     * Metodos set y is de las variable existe
     */
    public boolean isExiste() {
        return existe;
    }

    public void setExiste(boolean existe) {
        this.existe = existe;
    }
    
}
