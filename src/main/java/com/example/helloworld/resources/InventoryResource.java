package com.example.helloworld.resources;

import com.codahale.metrics.annotation.Timed;
import com.example.helloworld.HelloWorldApplication;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

/**
 * Created by vbathula on 10/2/16.
 */
@Path("/inventory")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class InventoryResource {

    public InventoryResource() {

    }

    @Path("/get")
    @GET
    @Timed
    public List<Mobile> getInventory() throws IOException {

        return HelloWorldApplication.getInventory().get();
        //System.out.println(i.get().get(0));
    }

//    @Path("/fill")
//    @POST
//    @Timed
//    public void fillInventory(JSONArray ){
//
//
//    }

    @POST
    @Path("/add/mobile/")
    @Timed
    public void addMobile(Mobile mobile) throws IOException {

        HelloWorldApplication.getInventory().add(mobile);

    }

    @POST
    @Path("/delete/mobile")
    @Timed
    public void deleteMobile(Mobile mobile) throws IOException, URISyntaxException {

        HelloWorldApplication.getInventory().delete(mobile);
    }
}
