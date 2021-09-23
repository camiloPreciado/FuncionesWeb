package com.udec.funcionesweb.controller;

import com.udec.funcionesweb.Dto.EstudianteDto;
import com.udec.funcionesweb.Service.EstudianteService;
import java.util.List;
import javax.ejb.Stateless;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

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
    /*    if(listaEstudiantes.isEmpty()){
            return Response.status(Response.Status.NO_CONTENT).entity("El archivo se encuentra vacio")
                .build();
        }else{
            return Response.status(Response.Status.OK).entity(listaEstudiantes).build();
        }*/
        return listaEstudiantes;
    }   
    
    @GET
    @Path("/obtenerPorCedula/{cedula}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerPorCedula(@PathParam("cedula") String cedula){
        EstudianteDto estudiante;
        EstudianteService servicioObtenerPorCedula = new EstudianteService();
        servicioObtenerPorCedula.obtenerPorCedula(cedula);
        estudiante = servicioObtenerPorCedula.getEstudiante();
        System.out.println(estudiante);
        if(estudiante.getCedula() == null){
            return Response.status(Response.Status.NOT_FOUND).entity("La cedula no se encuentra registrada")
                .build();
        }
        return Response.status(Response.Status.OK).entity(estudiante).build();
    } 
    
    @POST
    @Path("/agregar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON) 
    public Response agregar(@Valid EstudianteDto estudiante){
        EstudianteService servicioAgregar = new EstudianteService();
        servicioAgregar.agregar(estudiante);
        boolean i = servicioAgregar.isExiste();
        System.out.println(i);
        if(i == true){
            return Response.status(Response.Status.CONFLICT).entity("Esta cedula ya se encuentra registrada")
                    .build();
        }        
        return Response.status(Response.Status.CREATED).entity("Estudiante agregado con exito")
                .build();
    }
    
    @DELETE
    @Path("/eliminarPorCedula/{cedula}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response elminarPorCedula(@PathParam("cedula") String cedula){
        EstudianteService servicioEliminar = new EstudianteService();
        servicioEliminar.elminarPorCedula(cedula);
        boolean i = servicioEliminar.isExiste();
        System.out.println(i);
        if(i == false){
            return Response.status(Response.Status.NOT_FOUND).entity("Cedula no registrada")
                    .build();
        }        
        return Response.status(Response.Status.OK).entity("Estudiante eliminado con exito")
                .build();
    }
    
    @PUT
    @Path("/editar")
    public Response editar(@Valid EstudianteDto estudiante){
        EstudianteService servicioEditar = new EstudianteService();
        servicioEditar.editar(estudiante);
        boolean i = servicioEditar.isExiste();
        System.out.println(i);
        if(i == false){
            return Response.status(Response.Status.NOT_FOUND).entity("Cedula no registrada")
                    .build();
        }  
        return Response.status(Response.Status.OK).entity("Estudiante actualizado correctamente")
                .build();
    }
}
