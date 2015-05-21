package fr.univtln.madapm.votemanager.rest;

import fr.univtln.madapm.votemanager.dao.CUserDAO;
import fr.univtln.madapm.votemanager.dao.CVoteDAO;
import fr.univtln.madapm.votemanager.metier.user.CUser;
import fr.univtln.madapm.votemanager.metier.vote.CCandidate;
import fr.univtln.madapm.votemanager.metier.vote.CVote;

import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by civars169 on 06/05/15.
 * copyright Christian
 */

@Path("/vote")
public class CVoteRest {
/*
    @GET
    @Produces(MediaType.APPLICATION_XML)
    @Path("{id}")
    public CGroupe read(@PathParam("id") long id) {
        return entityManager.find(CGroupe.class, id);
    }
*/

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void addVote(CVote pNewVote){
        CVoteDAO lVoteDao=new CVoteDAO();
        //List<CCandidate> lCandidats=pNewVote.getCandidates();
        //pNewVote.setCandidates(null);
        lVoteDao.create(pNewVote);
    }
}
