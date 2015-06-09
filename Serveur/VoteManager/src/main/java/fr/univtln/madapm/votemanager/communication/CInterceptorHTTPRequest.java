package fr.univtln.madapm.votemanager.communication;

import fr.univtln.madapm.votemanager.crypto.CCrypto;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

/**
 * Created by civars169 on 20/05/15.
 */
@Provider
public class CInterceptorHTTPRequest implements ContainerRequestFilter {

    @Override
    public void filter(ContainerRequestContext pContainerRequestContext) throws IOException {
        String lCrypted=pContainerRequestContext.getHeaderString("Crypted");
        InputStream lEntityStream = pContainerRequestContext.getEntityStream();
        ByteArrayOutputStream lBaos = new ByteArrayOutputStream();
        byte[] lBuffer = new byte[4096];
        int lLength;
        while ((lLength = lEntityStream.read(lBuffer,0,lBuffer.length)) >0) {
            lBaos.write(lBuffer, 0, lLength);
        }
        lBaos.flush();
        String lMsg=lBaos.toString();
        String lId=pContainerRequestContext.getHeaderString("ID");

        if(lId!=null){
            if(!lId.equals("15128")) {
                UUID lUUID= UUID.fromString(lId);
                CCrypto lCrypto = new CCrypto();
                try {
                    lMsg = lCrypto.publicDecrypt(lCrypto.getKeyMap().get(lUUID), Hex.decodeHex(lMsg.toCharArray()));
                } catch (DecoderException e) {
                    e.printStackTrace();
                }
            }
            pContainerRequestContext.setEntityStream(new ByteArrayInputStream(lMsg.getBytes()));
        }
        else{
            pContainerRequestContext.abortWith(Response.status(500).build());
        }
    }
}
