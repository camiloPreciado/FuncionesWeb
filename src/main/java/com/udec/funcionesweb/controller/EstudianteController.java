package com.udec.funcionesweb.controller;

import com.udec.funcionesweb.Dto.EstudianteDto;
import com.udec.funcionesweb.Service.EstudianteService;
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
       
    @GET
    @Path("/obtener")
    @Produces(MediaType.APPLICATION_JSON)
    public List<EstudianteDto> obtenerLista(){
        List<EstudianteDto> listaEstudiantes;
        EstudianteService servicioObtener = new EstudianteService();
        servicioObtener.obtenerLista();
        listaEstudiantes = servicioObtener.getListaEstudiantes();
        System.out.println(listaEstudiantes);
        return listaEstudiantes;
    }   
    
    @GET
    @Path("/obtenerPorCedula/{cedula}")
    @Produces(MediaType.APPLICATION_JSON)
    public EstudianteDto obtenerPorCedula(@PathParam("cedula") String cedula){
        EstudianteDto estudiante;
        EstudianteService servicioObtenerPorCedula = new EstudianteService();
        servicioObtenerPorCedula.obtenerPorCedula(cedula);
        estudiante = servicioObtenerPorCedula.getEstudiante();
        System.out.println(estudiante);
        return estudiante;
    } 
    
    @POST
    @Path("/agregar")
    @Consumes(MediaType.APPLICATION_JSON)
    //@Produces(MediaType.APPLICATION_JSON) 
    public void agregar(EstudianteDto estudiante){
        EstudianteService servicioAgregar = new EstudianteService();
        servicioAgregar.agregar(estudiante);
    }
    
    @DELETE
    @Path("/eliminarPorCedula/{cedula}")
    @Produces(MediaType.APPLICATION_JSON)
    public void elminarPorCedula(@PathParam("cedula") String cedula){
        EstudianteService servicioEliminar = new EstudianteService();
        servicioEliminar.elminarPorCedula(cedula);
    }
    
    @PUT
    @Path("/editar")
    //@Consumes(MediaType.APPLICATION_JSON)
    //@Produces(MediaType.APPLICATION_JSON) 
    public void editar(EstudianteDto estudiante){
        EstudianteService servicioEditar = new EstudianteService();
        servicioEditar.editar(estudiante);
    }
}
