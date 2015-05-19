package fr.univtln.madapm.votemanager.rest;

import fr.univtln.madapm.votemanager.dao.CCandidateDAO;
import fr.univtln.madapm.votemanager.metier.vote.CCandidate;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

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
    public void deleteCandidate(@PathParam("pId") int pId){
        CCandidateDAO lCandidateDAO = new CCandidateDAO();
        lCandidateDAO.deleteCandidate(pId);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public CCandidate updateCandidate(CCandidate pCandidate){
        CCandidateDAO lCandidateDAO = new CCandidateDAO();
        return lCandidateDAO.updateCandidate(pCandidate);
    }

}
