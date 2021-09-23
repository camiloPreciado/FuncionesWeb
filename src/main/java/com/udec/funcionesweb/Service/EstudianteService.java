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
import javax.validation.Valid;
import javax.ws.rs.core.Response;

/**
 *
 * @author Camilo Preciado
 */
public class EstudianteService {
    
    private List<EstudianteDto> listaEstudiantes;
    
    private EstudianteDto estudiante = new EstudianteDto();
    
    private boolean existe;
    
    public EstudianteService() {
    }
    
    
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
    
  //-----------------------------------------------------------------  
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
    
    //_----------------------------------------------------------------------------------------
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

    public boolean isExiste() {
        return existe;
    }

    public void setExiste(boolean existe) {
        this.existe = existe;
    }

    
    
    

  
    
}
