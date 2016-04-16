package com.webservice;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/rest")
public class RestConfig extends Application {
    private Set<Class<?>> resources = new HashSet<Class<?>>();

    public void ResteasyConfiguration () {
        resources.add(FilmService.class);
    }

    @Override
    public Set<Class<?>> getClasses() {
        return resources;
    }

}
