package fr.univtln.madapm.votemanager.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 * Created by civars169 on 06/05/15.
 * copyright Christian
 */

@Path("test")
public class CRest {

    // TODO: update the class to suit your needs

    // The Java method will process HTTP GET requests
    @GET
    // The Java method will produce content identified by the MIME Media
    // type "text/plain"
    @Produces("text/plain")
    public String getIt() {
        return "Plus vite l'algo!";
    }

}
