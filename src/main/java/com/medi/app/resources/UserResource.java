package com.medi.app.resources;

import com.medi.app.services.UserServiceImpl;
import com.medi.app.model.PatchOperation;
import com.medi.app.model.User;
import io.swagger.jaxrs.PATCH;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.Provider;
import java.net.URI;
import java.util.List;

/**
 * Created by vvicario on 23/05/17.
 */
@Path("users")
public class UserResource {

    private UserServiceImpl userService = new UserServiceImpl();

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @RolesAllowed("ADMIN")
    public Response getAllUsers() {
        return Response.ok().entity(userService.getUsers()).build();
    }

    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getUserById(@PathParam("id") Integer id){
        try{
            return Response.ok().entity(userService.findUserById(id)).build();
        }catch (NotFoundException e){
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }catch (Exception e){
            return Response.serverError().entity(e.getMessage()).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createUser(@Context UriInfo info, @Valid User user){
        Integer id = userService.createUser(user);
        URI uri = info.getRequestUriBuilder().path("{id}").build(id);
        return Response.created(uri).build();
    }

    @PUT
    @Consumes("application/json")
    @Path("/{id}")
    public Response updateUser(User user, @PathParam("id") Integer id) {
        try {
            userService.updateUser(user, id);
            return Response.noContent().build();
        } catch (NotFoundException nfe) {
            return Response.status(Response.Status.NOT_FOUND).entity(nfe.getMessage()).build();
        } catch (IllegalArgumentException iae) {
            return Response.status(Response.Status.BAD_REQUEST).entity(iae.getMessage()).build();
        } catch (Exception e){
            return Response.serverError().entity(e.getMessage()).build();
        }
    }


    @PATCH
    @Consumes("application/json")
    @Path("/{id}")
    public Response partialUpdate(List<PatchOperation> patchOperations, @PathParam("id") Integer id) {
        try {
            userService.updatePartialUser(patchOperations, id);
            return Response.noContent().build();
        } catch (NotFoundException nfe) {
            return Response.status(Response.Status.NOT_FOUND).entity(nfe.getMessage()).build();
        } catch (IllegalArgumentException iae) {
            return Response.status(Response.Status.BAD_REQUEST).entity(iae.getMessage()).build();
        } catch (Exception e){
            return Response.serverError().entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{id}")
     public Response deleteUser(@PathParam("id") Integer id){
        try{
            userService.deleteUser(id);
            return Response.noContent().build();
        }catch (NotFoundException e){
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }catch (Exception e){
            return Response.serverError().entity(e.getMessage()).build();
        }
    }

}

