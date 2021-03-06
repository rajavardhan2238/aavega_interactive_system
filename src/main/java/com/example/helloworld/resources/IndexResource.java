package com.example.helloworld.resources;

import com.codahale.metrics.annotation.Timed;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * Created by vbathula on 10/7/16.
 */

@Path("/")
@Produces(MediaType.TEXT_HTML)
public class IndexResource {

    @GET
    @Timed
    public InputStream getIndexPage() throws FileNotFoundException {

        File indexPage = new File("index.html");
        return new FileInputStream(indexPage);
    }

}
