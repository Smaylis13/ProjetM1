package fr.univtln.madapm.votemanager.rest;

import fr.univtln.madapm.votemanager.crypto.CCrypto;
import fr.univtln.madapm.votemanager.crypto.CCryptoBean;

import javax.crypto.spec.SecretKeySpec;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.UUID;

/**
 * Created by Ookami on 01/06/2015.
 */
@Path("/crypto")
public class CCryptoRest {

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response generateKeys(CCryptoBean pCryptoBean){
        CCrypto lCrypto=new CCrypto();
        BigInteger lPublicKey=lCrypto.receiveKeyParam(pCryptoBean.getmPublicKey(),pCryptoBean.getmUniqueKey());
        return Response.status(200).entity(lPublicKey).build();
    }


}
