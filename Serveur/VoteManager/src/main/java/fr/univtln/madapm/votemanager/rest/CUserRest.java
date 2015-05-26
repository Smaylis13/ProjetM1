package fr.univtln.madapm.votemanager.rest;

import fr.univtln.madapm.votemanager.crud.CCRUDServiceBean;
import fr.univtln.madapm.votemanager.crud.CConstantes;
import fr.univtln.madapm.votemanager.dao.CUserDAO;
import fr.univtln.madapm.votemanager.metier.user.CGroup;
import fr.univtln.madapm.votemanager.metier.user.CUser;
import org.eclipse.persistence.exceptions.DatabaseException;

import javax.persistence.PersistenceException;
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
    public Response addUser(CUser pNewUser){
        Map<String,String> lParams = new HashMap<>();
        lParams.put("emailUser",pNewUser.getmEmail());
        CUserDAO lUserDAO=new CUserDAO();
        List<CUser> lUsers=new ArrayList<>();
        lUsers=lUserDAO.findWithNamedQuery("CUser.findAll",lParams);
        if(lUsers.isEmpty()) {
            lUserDAO.create(pNewUser);
            return Response.status(201).entity(pNewUser.getmId()).build();
        }
        return Response.status(409).entity(0).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/connect")
    public Response logUser(CUser pUser){
        Map<String,String> lParams = new HashMap<>();
        lParams.put("emailUser",pUser.getmEmail());
        CUserDAO lUserDAO=new CUserDAO();
        List<CUser> lUsers=new ArrayList<>();
        lUsers=lUserDAO.findWithNamedQuery("CUser.findAll",lParams);
        if(!lUsers.isEmpty()) {
            CUser lFindedUser = lUsers.get(0);
            if ((pUser.getmEmail().equals(lFindedUser.getmEmail())) && (pUser.getmPassword().equals(lFindedUser.getmPassword())))
                return Response.status(200).entity(lFindedUser).build();

        }
        return Response.status(401).header("WWW-Authenticate", "xBasic realm=\"fake\"").build();
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
