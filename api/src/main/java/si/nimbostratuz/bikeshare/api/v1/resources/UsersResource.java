package si.nimbostratuz.bikeshare.api.v1.resources;


import si.nimbostratuz.bikeshare.models.entities.User;
import si.nimbostratuz.bikeshare.services.beans.UserBean;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@ApplicationScoped
@Path("users")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UsersResource {
    @Inject
    private UserBean userBean;

    @GET
    public Response getUsers() {
        return Response.ok(userBean.getAll()).build();
    }

    @GET
    @Path("{id}")
    public Response getUsers(@PathParam("id") Integer id) {
        return Response.ok(userBean.get(id)).build();
    }

    @POST
    public Response createUser(User user) {
        return Response.ok(userBean.create(user)).build();
    }

    @PUT
    @Path("{id}")
    public Response editUser(@PathParam("id") Integer id, User user) {
        return Response.ok(userBean.update(id, user)).build();
    }

    @DELETE
    @Path("{id}")
    public Response deleteUser(@PathParam("id") Integer id) {
        userBean.delete(id);

        return Response.status(Response.Status.NO_CONTENT).build();
    }
}
