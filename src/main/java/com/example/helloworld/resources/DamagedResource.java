package com.example.helloworld.resources;

import com.codahale.metrics.annotation.Timed;
import com.example.helloworld.HelloWorldApplication;

import javax.print.attribute.standard.Media;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by vbathula on 10/2/16.
 */
@Path("/damaged")
@Produces(MediaType.APPLICATION_JSON)
public class DamagedResource {

    public DamagedResource() {

    }

    @GET
    @Timed
    public List<Mobile> getDamagedMobiles(){

        return HelloWorldApplication.getInventory().getMobilesWithStatus("damaged");
    }


}
