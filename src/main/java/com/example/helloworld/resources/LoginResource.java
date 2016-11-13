package com.example.helloworld.resources;

import com.codahale.metrics.annotation.Timed;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.*;

/**
 * Created by vbathula on 10/7/16.
 */

public class LoginResource {

    public InputStream getLoginPage() throws FileNotFoundException {

        File loginFile = new File("login.html");

        return new FileInputStream(loginFile);
    }
}
