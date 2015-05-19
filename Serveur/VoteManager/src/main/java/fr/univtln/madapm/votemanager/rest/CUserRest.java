package fr.univtln.madapm.votemanager.rest;

import fr.univtln.madapm.votemanager.crud.CCRUDServiceBean;
import fr.univtln.madapm.votemanager.crud.CConstantes;
import fr.univtln.madapm.votemanager.dao.CUserDAO;
import fr.univtln.madapm.votemanager.metier.user.CGroup;
import fr.univtln.madapm.votemanager.metier.user.CUser;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

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
        System.out.println("HELLO");
        CUserDAO lUserDAO=new CUserDAO();
        lUserDAO.create(pNewUser);
        return pNewUser.getId();
    }

}
