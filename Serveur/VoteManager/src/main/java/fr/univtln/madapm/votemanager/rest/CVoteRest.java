package fr.univtln.madapm.votemanager.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.univtln.madapm.votemanager.CMainServer;
import fr.univtln.madapm.votemanager.communication.gcm.CContent;
import fr.univtln.madapm.votemanager.communication.gcm.CPost2Gcm;
import fr.univtln.madapm.votemanager.dao.CUserDAO;
import fr.univtln.madapm.votemanager.dao.CVoteDAO;
import fr.univtln.madapm.votemanager.metier.user.CUser;
import fr.univtln.madapm.votemanager.metier.vote.CCandidate;
import fr.univtln.madapm.votemanager.metier.vote.CVote;
import org.glassfish.grizzly.http.server.Request;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by civars169 on 06/05/15.
 * copyright Christian
 */

@Path("/vote")
public class CVoteRest {
    @Context
    public Request mRequest;

    private ObjectMapper mMapper=new ObjectMapper();

   @GET
   @Path("/{pIdVote}")
   @Produces(MediaType.APPLICATION_JSON)
   public Response getVote(@PathParam("pIdVote") int pId) throws JsonProcessingException {
       CVoteDAO lVoteDAO=new CVoteDAO();
       CVote lVote=lVoteDAO.findById(pId);
       if(lVote!=null)
           return Response.status(200).header("ID", mRequest.getHeader("ID")).entity(mMapper.writeValueAsString(lVote)).build();
       return Response.status(409).build();
   }

    @GET
    @Path("/{pIdVote}/candidats")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCandidates(@PathParam("pIdVote") int pId) throws JsonProcessingException {
        CVoteDAO lVoteDAO=new CVoteDAO();
        CVote lVote=lVoteDAO.findById(pId);
        List<CCandidate> lCandidates=lVote.getCandidates();
        if (lCandidates != null)
            return Response.status(200).header("ID", mRequest.getHeader("ID")).entity(mMapper.writeValueAsString(lCandidates)).build();
        return Response.status(409).build();
    }

    @GET
    @Path("/all/{pIdUser}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getVotesOfUser(@PathParam("pIdUser") int pId) throws JsonProcessingException {

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
        System.out.println(lIdVotes.toString());
        Map<String,Object> lParams = new HashMap<>();
        CVoteDAO lVoteDAO = new CVoteDAO();
        List<CVote> lVotes;
        if(!lIdVotes.isEmpty()) {
            lParams.put("User", lUser);
            lParams.put("IdVotes", lIdVotes);
            lVotes = lVoteDAO.findWithNamedQuery("CVote.findAllFromUser", lParams);
        }
        else{
            lParams.put("User", lUser);
            lVotes = lVoteDAO.findWithNamedQuery("CVote.findOrgaByUser", lParams);
        }
        String lRequestSQL="select * from vote where ID_UTILISATEUR="+pId+" or ID_VOTE in (select ID_VOTE from invitation where ID_UTILISATEUR="+pId+") ;";
        lVotes=lVoteDAO.findByNativeQuery(lRequestSQL,CVote.class);
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
        return Response.status(200).header("ID", mRequest.getHeader("ID")).entity(mMapper.writeValueAsString(lVotes)).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addVote(CVote pNewVote){
        List<CUser> lParticipant = pNewVote.getParticipatingUsers();
        CContent c = new CContent();
        for(CUser u : lParticipant){
            u.addParticipatingVotes(pNewVote);
            c.addRegId(CUserRest.getsIdDevice().get(u.getEmail()));
        }
        c.createData("Invitation","Vous êtes invité à participer à un vote : "+pNewVote.getVoteName());
        CPost2Gcm.post(CMainServer.API_KEY,c);
        System.out.println("TEST");
        List<CCandidate> lCandidates=pNewVote.getCandidates();
        System.out.println("TEST2");
        CVoteDAO lVoteDao=new CVoteDAO();
        System.out.println("TEST3");
        CVote lNewVote= lVoteDao.create(pNewVote);
        System.out.println("TEST4");
        for(CCandidate lCandidate:lCandidates) {
            System.out.println(lCandidate.toString());
            lCandidate.addVote(lNewVote);
        }
        System.out.println("TEST5");
        lNewVote.setCandidates(lCandidates);
        System.out.println("TEST6");
        lVoteDao.update(lNewVote);
        System.out.println("TEST7");
        return Response.status(200).build();
    }
}
