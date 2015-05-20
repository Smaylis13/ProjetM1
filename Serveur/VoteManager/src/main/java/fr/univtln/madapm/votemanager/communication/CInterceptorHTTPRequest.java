package fr.univtln.madapm.votemanager.communication;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import java.io.IOException;

/**
 * Created by civars169 on 20/05/15.
 */
public class CInterceptorHTTPRequest implements ContainerRequestFilter {
    @Override
    public void filter(ContainerRequestContext containerRequestContext) throws IOException {
        // TODO interceptor request
    }
}
