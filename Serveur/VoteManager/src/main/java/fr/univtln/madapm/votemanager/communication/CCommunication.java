package fr.univtln.madapm.votemanager.communication;

import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.server.ServerEndpoint;

/**
 * Created by civars169 on 12/05/15.
 * copyright Christian
 */

@ServerEndpoint("/VoteManagerServer")
public class CCommunication {

    @OnMessage
    public String handleMessage(String pmessage){
        return "Message reçu";
    }

    @OnError
    public String whenError(){
        return "Erreur";
    }

}
