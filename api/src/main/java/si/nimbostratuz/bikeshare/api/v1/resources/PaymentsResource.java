package si.nimbostratuz.bikeshare.api.v1.resources;

import si.nimbostratuz.bikeshare.services.beans.PaymentBean;
import si.nimbostratuz.bikeshare.models.entities.Payment;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@ApplicationScoped
@Path("bicycles")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PaymentsResource {


    @Inject
    private PaymentBean paymentBean;

    @GET
    public Response getPayments() {

        return Response.ok(paymentBean.getAll()).build();
    }

    @GET
    @Path("{id}")
    public Response getPayments(@PathParam("id") Integer id) {

        return Response.ok(paymentBean.get(id)).build();
    }

    @POST
    public Response createPayment(Payment payment) {

        return Response.ok(paymentBean.create(payment)).build();
    }

    @PUT
    @Path("{id}")
    public Response updatePayment(@PathParam("id") Integer id, Payment payment) {

        return Response.ok(paymentBean.update(id, payment)).build();
    }

    @DELETE
    @Path("{id}")
    public Response deletePayment(@PathParam("id") Integer id) {

        paymentBean.delete(id);

        return Response.status(Response.Status.NO_CONTENT).build();
    }
}
