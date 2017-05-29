package com.medi.app.resources;

import com.medi.app.model.User;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by vvicario on 23/05/17.
 */
@Path("users")
public class UserResource {

    private static List<User> users = new ArrayList<User>(Arrays.asList(new User("Lucas", "Perez", 1)));
    private static Integer ID = 1;



    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getAllUsers() {
        return Response.ok().entity(users).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createUser(@Context UriInfo info, User user){
        users.add(new User(user.getName(), user.getSurname(), ++ID));
        URI uri = info.getRequestUriBuilder().path("{id}").build(ID);
        return Response.created(uri).build();
    }


}

