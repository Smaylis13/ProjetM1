package fr.univtln.madapm.votemanager.rest;

import fr.univtln.madapm.votemanager.dao.CRuleDAO;
import fr.univtln.madapm.votemanager.metier.vote.CRule;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by sebastien on 26/05/15.
 */

@Path("/rule")
public class CRuleRest {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{pId}")
    public CRule read(@PathParam("pId") int pId){
        CRuleDAO lRuleDAO = new CRuleDAO();
        return lRuleDAO.findById(pId);
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public CRule addRule(CRule pRule){
        CRuleDAO lRuleDAO = new CRuleDAO();
        return lRuleDAO.createRule(pRule);
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteRule(@PathParam("pId") int pId){
        CRuleDAO lRuleDAO = new CRuleDAO();
        lRuleDAO.deleteRule(pId);
        return Response.status(Response.Status.NO_CONTENT)// 204
                .entity("Rule has been removed").build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public CRule updateRule(CRule pRule){
        CRuleDAO lRuleDAO = new CRuleDAO();
        return lRuleDAO.updateRule(pRule);
    }
}
