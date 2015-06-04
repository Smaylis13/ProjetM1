package fr.univtln.madapm.votemanager.rest;

import fr.univtln.madapm.votemanager.dao.CUserDAO;
import fr.univtln.madapm.votemanager.metier.user.CUser;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.*;

/**
 * Created by civars169 on 06/05/15.
 * copyright Christian
 */


@Path("/user")
public class CUserRest {

    private static Map<String,String> sIdDevices = new HashMap<>();

    public static Map<String, String> getsIdDevice() {
        return sIdDevices;
    }

    @GET
    @Path("/regId/all")
    @Produces("application/json")
    public Collection<String> getsIdDevices(){
        return sIdDevices.values();
    }

    @GET
    @Path("/regId/{pMail}/")
    @Produces("text/plain")
    public String getRegId(@PathParam("pMail") String pEmail){
        return sIdDevices.get(pEmail);
    }

    @POST
    @Path("/regId/{emailC}/{regId}")
    public Response registerId(@PathParam("emailC") String pEmail,@PathParam("regId") String pRegId){
            sIdDevices.put(pEmail,pRegId);
            return Response.status(Response.Status.OK).entity("Device registred.").build();
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{pId}")
    public CUser read(@PathParam("pId") int pId) {
        CUserDAO lUserDAO=new CUserDAO();
        return lUserDAO.findByID(pId);
    }

    @GET
    @Path("/contact/{pIdU}")
    public Response getContacts(@PathParam("pIdU") int pIdU){
        System.out.println("getContact");
        CUserDAO lUserDAO=new CUserDAO();
        CUser lUser=lUserDAO.findByID(pIdU);
        lUserDAO.detach(lUser);
        List<CUser> lContactsUser=lUser.obtainContacts();
        /*lUserDAO.startTransac();
        for(CUser lContact:lContactsUser) {
            lContact.setPassword("");
        }
        List<CUser> lContacts=new ArrayList<>();
        lContacts.addAll(lContactsUser);
        lUserDAO.rollBackTransac();
        lUserDAO.update(lUser);*/
        return Response.status(200).entity(lContactsUser).build();
    }

    @PUT
    @Path("/contact/{pIdU}/{emailC}")
    public Response addContact(@PathParam("pIdU") int pIdU,@PathParam("emailC") String pEmailC){
        System.out.println("addContact");
        CUserDAO lUserDAO=new CUserDAO();
        CUser lUser=lUserDAO.findByID(pIdU);
        Map<String,String> lParams = new HashMap<>();
        lParams.put("emailUser",pEmailC);
        List<CUser> lUsers;
        lUsers=lUserDAO.findWithNamedQuery("CUser.findAll",lParams);
        if(lUsers.isEmpty()) {
            //TODO treatment for a non-existing invited user
            return Response.status(456).build();
        }

        lUser.addContact(lUserDAO.findByID(lUsers.get(0).getUserId()));
        lUserDAO.update(lUser);
        return Response.status(200).build();
    }


    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addUser(CUser pNewUser){
        System.out.println("addUser");
        Map<String,String> lParams = new HashMap<>();
        lParams.put("emailUser",pNewUser.getEmail());

        CUserDAO lUserDAO=new CUserDAO();
        List<CUser> lUsers;
        lUsers=lUserDAO.findWithNamedQuery("CUser.findAll",lParams);
        if(lUsers.isEmpty()) {
            lUserDAO.create(pNewUser);
            return Response.status(201).entity(pNewUser.getUserId()).build();
        }
        else if(lUsers.get(0).getPassword().equals("attente")){
            CUser lExistingUser=lUsers.get(0);
            lExistingUser.setFirstName(pNewUser.getFirstName());
            lExistingUser.setName(pNewUser.getName());
            lExistingUser.setPassword(pNewUser.getPassword());
            lUserDAO.update(lExistingUser);
            return Response.status(201).entity(lExistingUser.getUserId()).build();
        }
        return Response.status(409).entity(0).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/connect")
    public Response logUser(CUser pUser){
        System.out.println("logUser");
        Map<String,String> lParams = new HashMap<>();
        lParams.put("emailUser",pUser.getEmail());
        CUserDAO lUserDAO=new CUserDAO();
        List<CUser> lUsers;
        lUsers=lUserDAO.findWithNamedQuery("CUser.findAll",lParams);
        if(!lUsers.isEmpty()) {
            CUser lFindedUser = lUsers.get(0);
            if ((pUser.getEmail().equals(lFindedUser.getEmail())) && (pUser.getPassword().equals(lFindedUser.getPassword())))
                return Response.status(200).entity(lFindedUser).build();

        }
        return Response.status(401).header("WWW-Authenticate", "xBasic realm=\"fake\"").build();
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{emailC}/{password}")
    public Response removeUser(@PathParam("emailC") String pEmail,@PathParam("password") String pPassword){
        System.out.println("removeUser");
        Map<String,String> lParams = new HashMap<>();
        lParams.put("emailUser",pEmail);
        CUserDAO lUserDAO=new CUserDAO();
        List<CUser> lUsers=lUserDAO.findWithNamedQuery("CUser.findAll",lParams);
        if(!lUsers.isEmpty()){
            CUser lUser=lUsers.get(0);
            if(lUser.getPassword().equals(pPassword)) {
                lUserDAO.deleteUser(lUser.getUserId());

                return Response.status(Response.Status.OK).entity("User has been removed").build();
            }
        }

        return Response.status(401).header("WWW-Authenticate", "xBasic realm=\"fake\"").build();
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/contact/{idU}/{idC}")
    public Response removeContact(@PathParam("idU") int pIdU,@PathParam("idC") int pIdC){
        CUserDAO lUserDAO=new CUserDAO();
        CUser lUser =lUserDAO.findByID(pIdU);
        lUser.obtainContacts().remove(lUserDAO.findByID(pIdC));
        lUserDAO.update(lUser);
        return Response.status(Response.Status.OK).entity("Contact has been removed").build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateUser(CUser pNewUserParams){
        CUserDAO lUserDAO=new CUserDAO();
        Map<String,String> lParams = new HashMap<>();
        lParams.put("emailUser",pNewUserParams.getEmail());
        CUser lUser=lUserDAO.findByID(pNewUserParams.getUserId());
        if(!lUser.getEmail().equals(pNewUserParams.getEmail())) {
            List<CUser> lUsers = lUserDAO.findWithNamedQuery("CUser.findAll", lParams);
            if (lUsers.isEmpty()) {
                lUser.setName(pNewUserParams.getName());
                lUser.setFirstName(pNewUserParams.getFirstName());
                lUser.setPassword(pNewUserParams.getPassword());
                lUser.setEmail(pNewUserParams.getEmail());
                return Response.status(200).build();
            }
            else{
                return Response.status(455).entity("L'email existe déjà").build();
            }
        }
        else{
            lUser.setName(pNewUserParams.getName());
            lUser.setFirstName(pNewUserParams.getFirstName());
            lUser.setPassword(pNewUserParams.getPassword());
            return Response.status(200).build();
        }

    }

}
