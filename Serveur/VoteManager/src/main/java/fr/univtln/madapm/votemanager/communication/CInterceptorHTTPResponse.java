package fr.univtln.madapm.votemanager.communication;

import fr.univtln.madapm.votemanager.crypto.CCrypto;
import org.apache.commons.codec.binary.Hex;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.util.UUID;

/**
 * Created by Ookami on 06/06/2015.
 */
@Provider
public class CInterceptorHTTPResponse implements ContainerResponseFilter {

    @Override
    public void filter(ContainerRequestContext pRequestContext, ContainerResponseContext pResponseContext)
            throws IOException {
        CCrypto lCrypto=new CCrypto();
        String lId=pResponseContext.getHeaderString("ID");

        if(lId!=null&&!lId.equals("15128")) {
            UUID lUUID= UUID.fromString(lId);
            pResponseContext.setEntity(new String(Hex.encodeHex(lCrypto.publicEncrypt(pResponseContext.getEntity().toString(), lCrypto.getKeyMap().get(lUUID)))));
        }
    }
}
