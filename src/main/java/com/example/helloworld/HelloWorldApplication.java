package com.example.helloworld;

import com.example.helloworld.health.TemplateHealthCheck;
import com.example.helloworld.resources.*;
import com.fasterxml.jackson.databind.ser.impl.IndexedStringListSerializer;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import java.io.IOException;

/**
 * Created by vbathula on 9/25/16.
 */
public class HelloWorldApplication extends Application<HelloWorldConfiguration> {

    static Inventory inventory;

    public static void main(String[] args) throws Exception{
        inventory = new Inventory();
        new HelloWorldApplication().run(args);
    }

    @Override
    public String getName(){

        return "hello-world";
    }

    @Override
    public void initialize(Bootstrap<HelloWorldConfiguration> bootstrap) {

        try {
            inventory.fill();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void run(HelloWorldConfiguration configuration, Environment environment) throws Exception {

        final HelloWorldResource resource = new HelloWorldResource(
                configuration.getTemplate(),
                configuration.getDefaultName()
        );


        final TemplateHealthCheck healthCheck =
                new TemplateHealthCheck(configuration.getTemplate());
        environment.healthChecks().register("template", healthCheck);

        environment.jersey().register(resource);
        environment.jersey().register(InventoryResource.class);
        environment.jersey().register(IssuedResource.class);
        environment.jersey().register(AvailableResource.class);
        environment.jersey().register(DamagedResource.class);
        environment.jersey().register(LoginResource.class);
        environment.jersey().register(IndexResource.class);
    }

    public static Inventory getInventory() {
        return inventory;
    }
}
