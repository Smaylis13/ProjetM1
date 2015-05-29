package fr.univtln.madapm.votemanager.rest;

import fr.univtln.madapm.votemanager.dao.CUserDAO;
import fr.univtln.madapm.votemanager.dao.CVoteDAO;
import fr.univtln.madapm.votemanager.metier.user.CUser;
import fr.univtln.madapm.votemanager.metier.vote.CCandidate;
import fr.univtln.madapm.votemanager.metier.vote.CVote;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by civars169 on 06/05/15.
 * copyright Christian
 */

@Path("/vote")
public class CVoteRest {

   @GET
   @Path("/{pIdVote}")
   @Produces(MediaType.APPLICATION_JSON)
   public Response getVote(@PathParam("pIdVote") int pId){
       CVoteDAO lVoteDAO=new CVoteDAO();
       CVote lVote=lVoteDAO.findById(pId);
       if(lVote!=null)
           return Response.status(200).entity(lVote).build();
       return Response.status(409).build();
   }

    @GET
    @Path("/all/{pIdUser}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getVotesOfUser(@PathParam("pIdUser") int pId){
        SimpleDateFormat lSdf = new SimpleDateFormat("yyyy-MM-dd");
        Date lToday=new Date();
        CUserDAO lUserDAO=new CUserDAO();
        CUser lUser=lUserDAO.findByID(pId);
        List<Integer> lIdVotes=lUser.obtainParticipatingVotesIds();
        Map<String,Object> lParams = new HashMap<>();
        CVoteDAO lVoteDAO = new CVoteDAO();
        List<CVote> lVotes=null;
        if(!lIdVotes.isEmpty()) {
            lParams.put("User", lUser);
            lParams.put("IdVotes", lIdVotes);
            lVotes = lVoteDAO.findWithNamedQuery("CVote.findAllFromUser", lParams);
        }
        else{
            lParams.put("User", lUser);
            lVotes = lVoteDAO.findWithNamedQuery("CVote.findOrgaByUser", lParams);
        }
        for(CVote lVote:lVotes){
            lParams.clear();
            lParams.put("User",lUser);
            lParams.put("Vote",lVote);
            List<Integer> lChoix=lVoteDAO.findWithNamedQuery("CUser.findChoicesForVote",lParams);
            if(lChoix.isEmpty())
               lVote.setterVoted(false);
            else
                lVote.setterVoted(true);
            if(lVote.getStatusVote())
                System.out.println(lVote.getDateFin());
                 /*if(lVote.getDateFin().compareTo(lSdf.(lToday))<0)
                    lVote.setStatusVote(false);*/
            lVoteDAO.update(lVote);
        }
        return Response.status(200).entity(lVotes).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addVote(CVote pNewVote){
        System.out.println(pNewVote);
        CVoteDAO lVoteDao=new CVoteDAO();
        //List<CCandidate> lCandidats=pNewVote.getCandidates();
        //pNewVote.setCandidates(null);
        lVoteDao.create(pNewVote);
        return Response.status(201).build();
    }
}
