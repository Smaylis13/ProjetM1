package fr.univtln.madapm.votemanager.rest;

import fr.univtln.madapm.votemanager.dao.CGroupDAO;
import fr.univtln.madapm.votemanager.metier.user.CGroup;


import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by civars169 on 06/05/15.
 * copyright Christian
 */

@Path("/group")
public class CGroupRest {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{pId}")
    public CGroup read(@PathParam("pId") int pId){
        CGroupDAO lGroupDAO = new CGroupDAO();
        return lGroupDAO.findById(pId);
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public CGroup addGroup(CGroup pGroup){
        CGroupDAO lGroupDAO = new CGroupDAO();
        return lGroupDAO.createGroup(pGroup);
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteGroup(@PathParam("pId") int pId){
        CGroupDAO lGroupDAO = new CGroupDAO();
        lGroupDAO.deleteGroup(pId);
        return Response.status(Response.Status.NO_CONTENT)// 204
                .entity("Group has been removed").build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public CGroup updateGroup(CGroup pGroup){
        CGroupDAO lGroupDAO = new CGroupDAO();
        return lGroupDAO.updateGroup(pGroup);
    }
}
