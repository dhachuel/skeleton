package controllers;

import api.NetID;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Context;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import io.dropwizard.jersey.sessions.Session;

// For a Java class to be eligible to receive ANY requests
// it must be annotated with at least @Path
@Path("")
@Produces(MediaType.TEXT_PLAIN)
public class NetIDController {
    final String netid;

    public NetIDController(NetID netid) {
        this.netid = netid.netid;
    }

    // You can specify additional @Path steps; they will be relative
    // to the @Path defined at the class level
    @GET
    @Path("/")
    public String getDefaultNetID() {
        return this.netid;
    }

    // You can specify additional @Path steps; they will be relative
    // to the @Path defined at the class level
    @GET
    @Path("/netid")
    public String getNetID() {
        return this.netid;
    }
}
