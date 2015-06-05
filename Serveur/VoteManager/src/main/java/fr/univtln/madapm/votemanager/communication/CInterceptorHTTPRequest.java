package fr.univtln.madapm.votemanager.communication;

import fr.univtln.madapm.votemanager.crypto.CCrypto;
import org.glassfish.jersey.internal.util.Base64;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
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

        if(lCrypted==null||lCrypted=="yes"){
            /*System.out.println(lMsg);
            String lId=pContainerRequestContext.getHeaderString("ID");
            UUID lUUID= UUID.fromString(lId);
            System.out.println("INTERCEPTOR");
            lMsg= Base64.decodeAsString(lBaos.toByteArray());
            CCrypto lCrypto=new CCrypto();
            System.out.println("CRYPTED MESSAGE  "+lMsg);
            lMsg=lCrypto.publicDecrypt(lCrypto.getKeyMap().get(lUUID), lBaos.toByteArray());*/
        }
            //DECODER lMsg
        System.out.println("ICI   "+lMsg);
        System.out.println("test "+lCrypted);


        pContainerRequestContext.setEntityStream(new ByteArrayInputStream(lMsg.getBytes()));
    }
}
