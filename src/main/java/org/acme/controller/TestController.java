package org.acme.controller;

import org.acme.auth.RequirePermission;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

@Path("/test")
public class TestController {

    @GET
    @Path("/create")
    @RequirePermission("create:post")
    public Response createPost() {
        System.out.println("[DEBUG] Post create");
        return Response.ok("Post created").build();
    }

    @GET
    @Path("/delete")
    @RequirePermission("delete:post")
    public Response deletePost() {
        return Response.ok("Post deleted").build();
    }
}