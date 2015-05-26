package fr.univtln.madapm.votemanager.rest;

import fr.univtln.madapm.votemanager.dao.CCandidateDAO;
import fr.univtln.madapm.votemanager.metier.vote.CCandidate;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by sebastien on 18/05/15.
 */
@Path("/candidate")
public class CCandidateRest {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{pId}")
    public CCandidate readCandidate(@PathParam("pId") int pId){
        CCandidateDAO lCandidateDAO = new CCandidateDAO();
        return lCandidateDAO.findById(pId);
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public CCandidate addCandidate(CCandidate pCandidate){
        CCandidateDAO lCandidateDAO = new CCandidateDAO();
        return lCandidateDAO.createCandidate(pCandidate);
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteCandidate(@PathParam("pId") int pId){
        CCandidateDAO lCandidateDAO = new CCandidateDAO();
        lCandidateDAO.deleteCandidate(pId);
        return Response.status(Response.Status.NO_CONTENT).entity("Candidate had been removed").build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public CCandidate updateCandidate(CCandidate pCandidate){
        CCandidateDAO lCandidateDAO = new CCandidateDAO();
        return lCandidateDAO.updateCandidate(pCandidate);
    }

}
