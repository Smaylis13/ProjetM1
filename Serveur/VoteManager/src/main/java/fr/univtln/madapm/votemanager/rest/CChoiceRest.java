package fr.univtln.madapm.votemanager.rest;

import fr.univtln.madapm.votemanager.dao.CChoiceDAO;
import fr.univtln.madapm.votemanager.metier.vote.CChoice;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * Created by sebastien on 18/05/15.
 */
@Path("/choice")
public class CChoiceRest {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{pId}")
    public CChoice readChoice(@PathParam("pId") int pId){
        CChoiceDAO lChoiceDAO = new CChoiceDAO();
        return lChoiceDAO.findById(pId);
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public CChoice addChoice(CChoice pChoice){
        CChoiceDAO lChoiceDAO = new CChoiceDAO();
        return lChoiceDAO.createChoice(pChoice);
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public void deleteChoice(@PathParam("pId") int pId){
        CChoiceDAO lChoiceDAO = new CChoiceDAO();
        lChoiceDAO.deleteChoice(pId);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public CChoice updateChoice(CChoice pChoice){
        CChoiceDAO lChoiceDAO = new CChoiceDAO();
        return lChoiceDAO.updateChoice(pChoice);
    }
}
