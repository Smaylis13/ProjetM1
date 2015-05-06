package fr.univtln.madapm.votemanager;

import com.sun.jersey.api.container.grizzly2.GrizzlyServerFactory;
import com.sun.jersey.api.core.PackagesResourceConfig;
import com.sun.jersey.api.core.ResourceConfig;
import org.glassfish.grizzly.http.server.HttpServer;

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
        return UriBuilder.fromUri("http://localhost/VoteManger/").port(getPort(9999)).build();
    }

    public static final URI BASE_URI = getBaseURI();

    protected static HttpServer startServer() throws IOException {
        ResourceConfig resourceConfig = new PackagesResourceConfig("fr.univtln.madapm.votemanager");

        System.out.println("Starting grizzly2...");
        return GrizzlyServerFactory.createHttpServer(BASE_URI, resourceConfig);
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
        System.in.read();
        httpServer.stop();

        System.out.println("Fin de VoteManager");
    }
}
