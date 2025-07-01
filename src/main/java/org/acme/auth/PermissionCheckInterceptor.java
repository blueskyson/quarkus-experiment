package org.acme.auth;

import java.util.Set;

import jakarta.annotation.Priority;
import jakarta.inject.Inject;
import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InvocationContext;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.core.Response;

@Interceptor
@RequirePermission({})
@Priority(Interceptor.Priority.APPLICATION)
public class PermissionCheckInterceptor {
    @Inject
    ContainerRequestContext requestContext;

    @SuppressWarnings("unchecked")
    @AroundInvoke
    public Object checkPermission(InvocationContext context) throws Exception {
        System.out.println("[DEBUG] PermissionCheckInterceptor triggered");
        RequirePermission annotation = context.getMethod().getAnnotation(RequirePermission.class);
        if (annotation == null) {
            annotation = context.getTarget().getClass().getAnnotation(RequirePermission.class);
        }

        if (annotation == null) {
            return context.proceed(); // No RequirePermission annotation
        }

        Set<String> userPerms = (Set<String>) requestContext.getProperty("permissions");
        for (String required : annotation.value()) {
            if (!userPerms.contains(required)) {
                return Response.status(Response.Status.FORBIDDEN)
                        .entity("Missing permission: " + required)
                        .build();
            }
        }

        return context.proceed();
    }
}
