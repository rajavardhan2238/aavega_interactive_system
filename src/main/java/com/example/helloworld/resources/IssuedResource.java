package com.example.helloworld.resources;

import com.codahale.metrics.annotation.Timed;
import com.example.helloworld.HelloWorldApplication;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by vbathula on 10/2/16.
 */
@Path("/issued")
@Produces(MediaType.APPLICATION_JSON)
public class IssuedResource {

    public IssuedResource() {

    }

    @Path("/get")
    @GET
    @Timed
    public List<Mobile> getIssuedMobiles(){

        return HelloWorldApplication.getInventory().getMobilesWithStatus("issued");
    }
}
