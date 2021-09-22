package com.udec.funcionesweb.controller;

import com.udec.funcionesweb.Dto.EstudianteDto;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Camilo Preciado
 */
@Stateless
@Path("/estudiantes")
public class EstudianteController {
    
    
    @POST
    @Path("/agregar")
    @Consumes(MediaType.APPLICATION_JSON)
    //@Produces(MediaType.APPLICATION_JSON) 
    public void insertarPrueba(EstudianteDto estudiante){
        boolean existe = false;
        List<EstudianteDto> listaEstudiantes = new ArrayList<EstudianteDto>();
         FileInputStream ficheroL = null;     
         try {
             ficheroL = new FileInputStream("datos.txt");
             ObjectInputStream conexionF = new ObjectInputStream(ficheroL);
             listaEstudiantes = (List<EstudianteDto>)conexionF.readObject();
             
         } catch (FileNotFoundException ex) {
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
         /*   System.out.println("Registrado correctamente " + estudiante.getNombre());
            System.out.println("Registrado correctamente " + estudiante.getApellido());
            System.out.println("Registrado correctamente " + estudiante.getCedula());
            System.out.println("Registrado correctamente " + estudiante.getCorreo());
            System.out.println("Registrado correctamente " + estudiante.getEdad());
          */  FileOutputStream fichero = null;
            try {
                fichero = new FileOutputStream("datos.txt");
                ObjectOutputStream conexionF = new ObjectOutputStream(fichero);
                conexionF.writeObject(listaEstudiantes);
            } catch (FileNotFoundException ex) {
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
        else{
            System.out.println("esta cedula ya se encuentra registrada");
        }
    }
    
    @GET
    @Path("/obtener")
    @Produces(MediaType.APPLICATION_JSON)
    public List<EstudianteDto> obtener(){
        FileInputStream ficheroL = null;    
         List<EstudianteDto> listaEstudiantes = null;
         try {
             ficheroL = new FileInputStream("datos.txt");
             ObjectInputStream conexionF = new ObjectInputStream(ficheroL);
             listaEstudiantes = (List<EstudianteDto>)conexionF.readObject();      
         } catch (FileNotFoundException ex) {
             ex.printStackTrace();
         }catch  (IOException ex){
            ex.printStackTrace();
        }catch (ClassNotFoundException ex){
            ex.printStackTrace();
        }
        return listaEstudiantes;
    }   
    
    @GET
    @Path("/obtenerPorCedula/{cedula}")
    @Produces(MediaType.APPLICATION_JSON)
    public EstudianteDto obtenerPorCedula(@PathParam("cedula") String cedula){
        boolean encontrado = false;
        int posicion = 0;
        FileInputStream ficheroL = null;    
         List<EstudianteDto> listaEstudiantes = null;
         try {
             ficheroL = new FileInputStream("datos.txt");
             ObjectInputStream conexionF = new ObjectInputStream(ficheroL);
             listaEstudiantes = (List<EstudianteDto>)conexionF.readObject();
             
         } catch (FileNotFoundException ex) {
             ex.printStackTrace();
         }catch  (IOException ex){
            ex.printStackTrace();
        }catch (ClassNotFoundException ex){
            ex.printStackTrace();
        }
         for (int i=0; i<listaEstudiantes.size(); i++) {
            if(cedula.equalsIgnoreCase(listaEstudiantes.get(i).getCedula())){
                encontrado = true;
                posicion = i;
            }
        }
        return listaEstudiantes.get(posicion);
    } 
    
    
    @DELETE
    @Path("/eliminarPorCedula/{cedula}")
    @Produces(MediaType.APPLICATION_JSON)
    public void elminarPorCedula(@PathParam("cedula") String cedula){
        boolean encontrado = false;
        int posicion = 0;
        List<EstudianteDto> listaEstudiantes = new ArrayList<EstudianteDto>();
         FileInputStream ficheroL = null;     
         try {
             ficheroL = new FileInputStream("datos.txt");
             ObjectInputStream conexionF = new ObjectInputStream(ficheroL);
             listaEstudiantes = (List<EstudianteDto>)conexionF.readObject();
             
         } catch (FileNotFoundException ex) {
             ex.printStackTrace();
         }catch  (IOException ex){
            ex.printStackTrace();
        }catch (ClassNotFoundException ex){
            ex.printStackTrace();
        }
         
        for (int i=0; i<listaEstudiantes.size(); i++) {
            if(cedula.equalsIgnoreCase(listaEstudiantes.get(i).getCedula())){
                encontrado = true;
                posicion = i;
            }
        }
        
        if(encontrado == true){
            listaEstudiantes.remove(posicion);
            FileOutputStream fichero = null;
            try {
                fichero = new FileOutputStream("datos.txt");
                ObjectOutputStream conexionF = new ObjectOutputStream(fichero);
                conexionF.writeObject(listaEstudiantes);
            } catch (FileNotFoundException ex) {
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
            System.out.println("Estudiante Eliminado");
        }else{
            System.out.println("Documento no encontrado");
        }
    }
    
    @PUT
    @Path("/editar")
    //@Consumes(MediaType.APPLICATION_JSON)
    //@Produces(MediaType.APPLICATION_JSON) 
    public void editar(EstudianteDto estudiante){
        boolean encontrado = false;
        int posicion = 0;
        List<EstudianteDto> listaEstudiantes = new ArrayList<EstudianteDto>();
         FileInputStream ficheroL = null;     
         try {
             ficheroL = new FileInputStream("datos.txt");
             ObjectInputStream conexionF = new ObjectInputStream(ficheroL);
             listaEstudiantes = (List<EstudianteDto>)conexionF.readObject();
             
         } catch (FileNotFoundException ex) {
             ex.printStackTrace();
         }catch  (IOException ex){
            ex.printStackTrace();
        }catch (ClassNotFoundException ex){
            ex.printStackTrace();
        }
         
        for (int i=0; i<listaEstudiantes.size(); i++) {
            if(estudiante.getCedula().equalsIgnoreCase(listaEstudiantes.get(i).getCedula())){
                encontrado = true;
                posicion = i;
            }
        }
        
        if(encontrado == true){
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
            } catch (FileNotFoundException ex) {
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
        }else{
            System.out.println("Documento no encontrado");
        }
    }
}
