package fr.univtln.madapm.votemanager;


import fr.univtln.madapm.votemanager.communication.authentification.CDatabase;
import fr.univtln.madapm.votemanager.communication.authentification.CServlet;

import org.glassfish.grizzly.http.server.HttpHandler;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.server.Request;
import org.glassfish.grizzly.http.server.Response;
import org.glassfish.grizzly.servlet.ServletRegistration;
import org.glassfish.grizzly.servlet.WebappContext;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.grizzly2.servlet.GrizzlyWebContainerFactory;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;
import org.mockito.Mockito;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.UriBuilder;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.security.Principal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.CountDownLatch;

/**
 * Created by civars169 on 04/05/15.
 * copyright Christian
 */
public class CMainServer extends Application {

    private static CDatabase sDatabase=new CDatabase();

    public static CDatabase getDatabase() {
        return sDatabase;
    }

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
        return UriBuilder.fromUri("http://10.21.205.16/").port(getPort(80)).build();
    }

    public static final URI BASE_URI = getBaseURI();



    protected static HttpServer startServer() throws IOException {

        final ResourceConfig lrc = new ResourceConfig().packages("fr.univtln.madapm.votemanager");
        lrc.register(JacksonFeature.class);

        // create and start a new instance of grizzly http server
        // exposing the Jersey application at BASE_URI
       return GrizzlyHttpServerFactory.createHttpServer(BASE_URI, lrc);
        //return GrizzlyWebContainerFactory.create(BASE_URI);
    }

    public static void main(String[] args) throws IOException {

        System.out.println("Démarrage du programme de VoteManager");

        System.out.println("Démarrage du serveur...");

        System.out.println("Connection à la base de donnée");

        String url = "jdbc:mysql://localhost/VoteManager";
        /*String login = "server";
        String password = "root";
        */
        String login = "root";
        String password = "";

        Connection connection = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url, login, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        // Grizzly 2 initialization
        HttpServer httpServer = startServer();
        final WebappContext context=new WebappContext("Servlet","");
        final ServletRegistration register=context.addServlet("Servlet",new CServlet());
        register.addMapping("/auth/*");
        register.setInitParameter("fr.univtln.madapm.votemanager.communication.authentification", BASE_URI + ":9999");
        context.deploy(httpServer);
        //httpServer.start();


        System.out.println(String.format("Jersey app started with WADL available at "
                        + "%sapplication.wadl\nHit enter to stop it...",
                BASE_URI));

        /*Replace with
        while(true){
        }
        for the vps
        */
        CountDownLatch latch = new CountDownLatch(1);
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //System.in.read();
        //httpServer.stop();
        httpServer.shutdownNow();
        System.out.println("Serveur off");

        System.out.println("Fermeture connection BDD");
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Fin de VoteManager");
    }
}
