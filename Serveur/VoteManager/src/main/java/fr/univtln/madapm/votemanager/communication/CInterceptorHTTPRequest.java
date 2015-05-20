package fr.univtln.madapm.votemanager.communication;

import org.eclipse.persistence.oxm.MediaType;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

/**
 * Created by civars169 on 20/05/15.
 */
public class CInterceptorHTTPRequest implements ContainerRequestFilter {
    @Override
    public void filter(ContainerRequestContext pContainerRequestContext) throws IOException {

        InputStream lEntityStream = pContainerRequestContext.getEntityStream();
        ByteArrayOutputStream lBaos = new ByteArrayOutputStream();
        byte[] lBuffer = new byte[4096];
        int lLength;
        while ((lLength = lEntityStream.read(lBuffer,0,lBuffer.length)) >0) {
            lBaos.write(lBuffer, 0, lLength);
        }
        lBaos.flush();
        String lMsg=lBaos.toString();
        //DECODER lMsg

        pContainerRequestContext.setEntityStream(new ByteArrayInputStream(lMsg.getBytes()));
    }
}
