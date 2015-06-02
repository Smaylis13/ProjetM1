package fr.univtln.madapm.votemanager.rest;

import fr.univtln.madapm.votemanager.dao.CUserDAO;
import fr.univtln.madapm.votemanager.dao.CVoteDAO;
import fr.univtln.madapm.votemanager.metier.user.CUser;
import fr.univtln.madapm.votemanager.metier.vote.CCandidate;
import fr.univtln.madapm.votemanager.metier.vote.CVote;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.text.DateFormat;
import java.text.ParseException;
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
    @Path("/{pIdVote}/candidats")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCandidates(@PathParam("pIdVote") int pId){
        CVoteDAO lVoteDAO=new CVoteDAO();
        CVote lVote=lVoteDAO.findById(pId);
        List<CCandidate> lCandidates=lVote.getCandidates();
        if(lCandidates!=null)
            return Response.status(200).entity(lCandidates).build();
        return Response.status(409).build();
    }

    @GET
    @Path("/all/{pIdUser}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getVotesOfUser(@PathParam("pIdUser") int pId){
        SimpleDateFormat lSdf = new SimpleDateFormat("yyyy-MM-dd");
        Date lToday=new Date();
        Calendar lCalendar = Calendar.getInstance();
        lCalendar.setTime(lToday);
        lCalendar.set(Calendar.MILLISECOND, 0);
        lCalendar.set(Calendar.SECOND, 59);
        lCalendar.set(Calendar.MINUTE, 59);
        lCalendar.set(Calendar.HOUR_OF_DAY, 23);
        lToday=lCalendar.getTime();
        try {
            lToday=lSdf.parse(lSdf.format(lToday));
        } catch (ParseException e) {
            e.printStackTrace();
        }
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
                 if(lVote.getDateFin().compareTo(lToday)<0) {
                     lVote.setStatusVote(false);
                 }
            lVoteDAO.update(lVote);
        }
        //System.out.println(lVotes);
        return Response.status(200).entity(lVotes).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addVote(CVote pNewVote){
        List<CCandidate> lCandidates=pNewVote.getCandidates();
        CVoteDAO lVoteDao=new CVoteDAO();
        CVote lNewVote= lVoteDao.create(pNewVote);
        for(CCandidate lCandidate:lCandidates) {
            System.out.println(lCandidate.toString());
            lCandidate.addVote(lNewVote);
        }
        lNewVote.setCandidates(lCandidates);
        lVoteDao.update(lNewVote);
        return Response.status(200).build();
    }
}
