package fr.univtln.madapm.votemanager.rest;

import fr.univtln.madapm.votemanager.dao.CTypeDAO;
import fr.univtln.madapm.votemanager.metier.vote.CType;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by sebastien on 26/05/15.
 */
@Path("/type")
public class CTypeRest {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{pId}")
    public CType read(@PathParam("pId") int pId){
        CTypeDAO lTypeDAO = new CTypeDAO();
        return lTypeDAO.findById(pId);
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public CType addType(CType pType){
        CTypeDAO lTypeDAO = new CTypeDAO();
        return lTypeDAO.createType(pType);
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteType(@PathParam("pId") int pId){
        CTypeDAO lTypeDAO = new CTypeDAO();
        lTypeDAO.deleteType(pId);
        return Response.status(Response.Status.NO_CONTENT)// 204
                .entity("Type has been removed").build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public CType updateType(CType pType){
        CTypeDAO lTypeDAO = new CTypeDAO();
        return lTypeDAO.updateType(pType);
    }
}
