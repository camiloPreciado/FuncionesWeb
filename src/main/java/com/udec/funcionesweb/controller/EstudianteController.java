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
    @Path("/insertarPrueba")
    @Consumes(MediaType.APPLICATION_JSON)
    //@Produces(MediaType.APPLICATION_JSON) 
    public void insertarPrueba(EstudianteDto estudaiante){
     /*   System.out.println("Registrado correctamente " + estudaiante.getNombre());
        System.out.println("Registrado correctamente " + estudaiante.getApellido());
        System.out.println("Registrado correctamente " + estudaiante.getCedula());
        System.out.println("Registrado correctamente " + estudaiante.getCorreo());
        System.out.println("Registrado correctamente " + estudaiante.getEdad());
      */  FileOutputStream fichero = null;
        try {
            fichero = new FileOutputStream("datos.txt");
            ObjectOutputStream conexionF = new ObjectOutputStream(fichero);
            conexionF.writeObject(estudaiante);
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
        //return listaEstudiantes;
    }
    
     @GET
    @Path("/obtener")
    @Produces(MediaType.APPLICATION_JSON)
    public EstudianteDto obtener(){
        FileInputStream ficheroL = null;
        EstudianteDto e = null;      
         try {
             ficheroL = new FileInputStream("datos.txt");
             ObjectInputStream conexionF = new ObjectInputStream(ficheroL);
             e = (EstudianteDto)conexionF.readObject();
             
         } catch (FileNotFoundException ex) {
             ex.printStackTrace();
         }catch  (IOException ex){
            ex.printStackTrace();
        }catch (ClassNotFoundException ex){
            ex.printStackTrace();
        }
        return e;
    }   
    
}
