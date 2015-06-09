package fr.univtln.madapm.votemanager.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.univtln.madapm.votemanager.dao.CResultDAO;
import fr.univtln.madapm.votemanager.dao.CVoteDAO;
import fr.univtln.madapm.votemanager.metier.vote.CResult;
import fr.univtln.madapm.votemanager.metier.vote.CVote;
import org.glassfish.grizzly.http.server.Request;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by sebastien on 26/05/15.
 */

@Path("/result")
public class CResultRest {

    @Context
    public Request mRequest;

    private ObjectMapper mMapper=new ObjectMapper();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{pId}")
    public CResult read(@PathParam("pId") int pId){
        CResultDAO lResultDAO = new CResultDAO();
        return lResultDAO.findById(pId);
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Response addResult(List<CResult> pResult){
        System.out.println("ICI Result");
        CVoteDAO lVoteDAO=new CVoteDAO();
        System.out.println("là");
        CVote lVote=lVoteDAO.findById(pResult.get(0).getVote());
        System.out.println("là");
        System.out.println(pResult.get(0).toString());
        System.out.println("là");
        lVote.setResultVote(pResult);
        lVoteDAO.update(lVote);
        return Response.status(200).header("ID", mRequest.getHeader("ID")).entity("Results have been added").build();
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteResult(@PathParam("pId") int pId){
        CResultDAO lResultDAO = new CResultDAO();
        lResultDAO.deleteResult(pId);
        return Response.status(Response.Status.NO_CONTENT).header("ID", mRequest.getHeader("ID")).entity("Result has been removed").build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public void updateResult(CResult pResult){
        CResultDAO lResultDAO = new CResultDAO();
        lResultDAO.updateResult(pResult);
    }
}
