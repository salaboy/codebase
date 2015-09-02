/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.codebase.services.filters.auth;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author salaboy
 */
@Provider
@PreMatching
public class AuthRESTRequestFilter implements ContainerRequestFilter {

    private final static Logger log = Logger.getLogger(AuthRESTRequestFilter.class.getName());

    @Inject
    GrogAuthenticator grogAuthenticator;

    @Override

    public void filter(ContainerRequestContext requestCtx) throws IOException {

        String path = requestCtx.getUriInfo().getPath();

        log.info("Filtering request path: " + path);

        // IMPORTANT!!! First, Acknowledge any pre-flight test from browsers for this case before validating the headers (CORS stuff)
        if (requestCtx.getRequest().getMethod().equals("OPTIONS")) {

            requestCtx.abortWith(Response.status(Response.Status.OK).build());
            log.info("OPTIONS related request" + path);
            return;

        }

        if (!path.startsWith("/auth/register")
                && !path.startsWith("/static") && !path.startsWith("/auth/google")
                && !path.contains("public")) {
            
            // Then check is the service key exists and is valid.
            // For any pther methods besides login, the authToken and service key  must be verified
            if (!path.startsWith("/auth/login")) {
                log.info("Checking for correct service_key " + path);
                String serviceKey = requestCtx.getHeaderString(GrogHTTPHeaderNames.SERVICE_KEY);

                if (!grogAuthenticator.isServiceKeyValid(serviceKey)) {

                    // Kick anyone without a valid service key
                    requestCtx.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
                    log.severe("Wrong service_key " + path);
                    return;

                }

                log.info("Checking for correct auth_token: " + path);
                String authToken = requestCtx.getHeaderString(GrogHTTPHeaderNames.AUTH_TOKEN);

                // if it isn't valid, just kick them out.
                if (!grogAuthenticator.isAuthTokenValid(serviceKey, authToken)) {
                    log.severe("Wrong auth_token " + path);
                    requestCtx.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());

                }
                List<String> roles = grogAuthenticator.getUserRoles(serviceKey);

                if (path.contains("admin") && !roles.contains("Admin")) {

                    log.severe("You cannot access to this resources without out Admin role " + path);
                    requestCtx.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
                } else {
                    log.info("This is an admin request from ("+serviceKey+") to " + path + " - with roles = "+roles);
                }

            }
        }

    }

}
