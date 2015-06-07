package fr.univtln.madapm.votemanager.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.univtln.madapm.votemanager.dao.CChoiceDAO;
import fr.univtln.madapm.votemanager.metier.vote.CChoice;
import org.glassfish.grizzly.http.server.Request;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by sebastien on 18/05/15.
 */
@Path("/choice")
public class CChoiceRest {
    @Context
    public Request mRequest;

    private ObjectMapper mMapper=new ObjectMapper();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{pId}")
    public CChoice readChoice(@PathParam("pId") int pId){
        CChoiceDAO lChoiceDAO = new CChoiceDAO();
        return lChoiceDAO.findById(pId);
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Response addChoice(CChoice pChoice){
        CChoiceDAO lChoiceDAO = new CChoiceDAO();
        lChoiceDAO.createChoice(pChoice);
        return Response.status(200).build();
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/multiples")
    public Response addChoices(List<CChoice> pChoices){
        System.out.println("ADDCHOICES1");
        CChoiceDAO lChoiceDAO = new CChoiceDAO();
        System.out.println("ADDCHOICES2");
        for(CChoice lChoice:pChoices)
            lChoiceDAO.createChoice(lChoice);
        System.out.println("ADDCHOICES3");
        return Response.status(200).build();
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteChoice(@PathParam("pId") int pId){
        CChoiceDAO lChoiceDAO = new CChoiceDAO();
        lChoiceDAO.deleteChoice(pId);
        return Response.status(Response.Status.NO_CONTENT).header("ID", mRequest.getHeader("ID")).entity("Choice has been removed").build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public CChoice updateChoice(CChoice pChoice){
        CChoiceDAO lChoiceDAO = new CChoiceDAO();
        return lChoiceDAO.updateChoice(pChoice);
    }
}
