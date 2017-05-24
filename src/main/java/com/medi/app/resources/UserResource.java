package com.medi.app.resources;

import com.medi.app.model.User;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by vvicario on 23/05/17.
 */
@Path("users")
public class UserResource {

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getAllUsers() {
        return Response.ok().entity(new User("Veronica", "Vicario")).build();
    }
}

