package org.acme.auth;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import jakarta.annotation.Priority;
import jakarta.ws.rs.Priorities;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.ext.Provider;

@Provider
@Priority(Priorities.AUTHENTICATION)
public class RolePermissionFilter implements ContainerRequestFilter {
    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        String rolesHeader = requestContext.getHeaderString("X-User-Roles");
        String permsHeader = requestContext.getHeaderString("X-User-Permissions");

        Set<String> roles = rolesHeader != null ? new HashSet<>(Arrays.asList(rolesHeader.split(",")))
                : new HashSet<>();

        Set<String> permissions = permsHeader != null ? new HashSet<>(Arrays.asList(permsHeader.split(",")))
                : new HashSet<>();

        requestContext.setProperty("roles", roles);
        requestContext.setProperty("permissions", permissions);
    }
}
