package com.example.helloworld.resources;

import com.codahale.metrics.annotation.Timed;
import com.example.helloworld.HelloWorldApplication;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.*;
import java.util.List;

/**
 * Created by vbathula on 10/8/16.
 */
@Path("/device.html")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DeviceResource {

    @GET
    @Timed
    public List<Mobile> getInventory() throws IOException {

        return HelloWorldApplication.getInventory().get();
        //System.out.println(i.get().get(0));
    }

    @GET
    @Timed
    public InputStream getIndexPage() throws FileNotFoundException {

        File indexPage = new File("device.html");
        return new FileInputStream(indexPage);
    }

}
