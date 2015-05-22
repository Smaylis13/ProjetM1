package fr.univtln.madapm.votemanager.rest;

import fr.univtln.madapm.votemanager.crud.CCRUDServiceBean;
import fr.univtln.madapm.votemanager.crud.CConstantes;
import fr.univtln.madapm.votemanager.dao.CUserDAO;
import fr.univtln.madapm.votemanager.metier.user.CGroup;
import fr.univtln.madapm.votemanager.metier.user.CUser;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by civars169 on 06/05/15.
 * copyright Christian
 */


@Path("/user")
public class CUserRest {


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{pId}")
    public CUser read(@PathParam("pId") int pId) {
        CUserDAO lUserDAO=new CUserDAO();
        return lUserDAO.findByID(pId);
    }


    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public int addUser(CUser pNewUser){
        CUserDAO lUserDAO=new CUserDAO();
        lUserDAO.create(pNewUser);
        return pNewUser.getmId();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/connect")
    public Response logUser(CUser pUser){
        System.out.println("here");
        System.out.println(pUser.toString());
        Map<String,String> lParams = new HashMap<>();
        lParams.put("emailUser",pUser.getmEmail());
        CUserDAO lUserDAO=new CUserDAO();
        System.out.println("pass");
        List<CUser> lUsers=new ArrayList<>();
        lUsers=lUserDAO.findWithNamedQuery("CUser.findAll",lParams);
        CUser lFindedUser=lUsers.get(0);
        System.out.println(lFindedUser.toString());
        if ((pUser.getmEmail().equals(lFindedUser.getmEmail()))&&(pUser.getmPassword().equals(lFindedUser.getmPassword())))
            return Response.status(200).entity(lFindedUser).build();
        else
            return Response.status(401).build();
    }

    @DELETE
    @Path("/{pId}")
    public Response removeUser(@PathParam("pId") int pId){
        System.out.println(pId);
        CUserDAO lUserDAO=new CUserDAO();
        lUserDAO.deleteUser(pId);
        return Response.status(Response.Status.NO_CONTENT)// 204
                .entity("User has been removed").build();
    }

}
