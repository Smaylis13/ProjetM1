package fr.univtln.madapm.votemanager.rest;

import fr.univtln.madapm.votemanager.dao.CResultDAO;
import fr.univtln.madapm.votemanager.metier.vote.CResult;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by sebastien on 26/05/15.
 */

@Path("/result")
public class CResultRest {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{pId}")
    public CResult read(@PathParam("pId") int pId){
        CResultDAO lResultDAO = new CResultDAO();
        return lResultDAO.findById(pId);
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public CResult addResult(CResult pResult){
        CResultDAO lResultDAO = new CResultDAO();
        return lResultDAO.createResult(pResult);
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteResult(@PathParam("pId") int pId){
        CResultDAO lResultDAO = new CResultDAO();
        lResultDAO.deleteResult(pId);
        return Response.status(Response.Status.NO_CONTENT)// 204
                .entity("Result has been removed").build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public void updateResult(CResult pResult){
        CResultDAO lResultDAO = new CResultDAO();
        lResultDAO.updateResult(pResult);
    }
}
