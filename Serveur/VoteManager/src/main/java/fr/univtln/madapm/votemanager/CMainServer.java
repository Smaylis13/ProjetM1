package fr.univtln.madapm.votemanager;


import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.UriBuilder;
import java.io.IOException;
import java.net.URI;

/**
 * Created by civars169 on 04/05/15.
 * copyright Christian
 */
public class CMainServer extends Application {

    private static int getPort(int defaultPort) {
        //grab port from environment, otherwise fall back to default port 9999
        String httpPort = System.getProperty("jersey.test.port");
        if (null != httpPort) {
            try {
                return Integer.parseInt(httpPort);
            } catch (NumberFormatException e) {
            }
        }
        return defaultPort;
    }

    private static URI getBaseURI() {
        return UriBuilder.fromUri("http://localhost/").port(getPort(9999)).build();
    }

    public static final URI BASE_URI = getBaseURI();

    protected static HttpServer startServer() throws IOException {

        final ResourceConfig lrc = new ResourceConfig().packages("fr.univtln.madapm.votemanager");
        lrc.register(JacksonFeature.class);

        // create and start a new instance of grizzly http server
        // exposing the Jersey application at BASE_URI
        return GrizzlyHttpServerFactory.createHttpServer(BASE_URI, lrc);
    }

    public static void main(String[] args) throws IOException {

        System.out.println("Démarrage du programme de VoteManager");

        System.out.println("Démarrage du serveur...");

        System.out.println("Connection à la base de donnée");

        // Grizzly 2 initialization
        HttpServer httpServer = startServer();
        System.out.println(String.format("Jersey app started with WADL available at "
                        + "%sapplication.wadl\nHit enter to stop it...",
                BASE_URI));

        /*Replace with
        while(true){
        }
        for the vps
        */
        System.in.read();
        //httpServer.stop();
        httpServer.shutdownNow();

        System.out.println("Fin de VoteManager");
    }
}
