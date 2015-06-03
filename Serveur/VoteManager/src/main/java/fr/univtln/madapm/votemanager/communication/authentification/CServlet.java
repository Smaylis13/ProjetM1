package fr.univtln.madapm.votemanager.communication.authentification;


import fr.univtln.madapm.votemanager.CMainServer;
import org.apache.oltu.oauth2.as.issuer.MD5Generator;
import org.apache.oltu.oauth2.as.issuer.OAuthIssuer;
import org.apache.oltu.oauth2.as.issuer.OAuthIssuerImpl;
import org.apache.oltu.oauth2.as.request.OAuthTokenRequest;
import org.apache.oltu.oauth2.as.response.OAuthASResponse;
import org.apache.oltu.oauth2.common.OAuth;
import org.apache.oltu.oauth2.common.error.OAuthError;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.OAuthResponse;
import org.apache.oltu.oauth2.common.message.types.GrantType;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Response;

/**
 * Created by Ookami on 21/05/2015.
 */
public class CServlet extends HttpServlet {



    private static final long serialVersionUID = 1L;
    public static final String INVALID_CLIENT_DESCRIPTION = "Client authentication failed (e.g., unknown client, no client authentication included, or unsupported authentication method).";

    private String message;

    public void init() throws ServletException
    {
        // Do required initialization
        message = "Hello World";
    }

        public void doPost( HttpServletRequest request, HttpServletResponse lResponse) throws ServletException, java.io.IOException {

            try {
                OAuthTokenRequest oauthRequest = new OAuthTokenRequest(request);
                OAuthIssuer oauthIssuerImpl = new OAuthIssuerImpl(new MD5Generator());
                // check if clientid is valid
                if (!checkClientId(oauthRequest.getClientId())) {
                    buildInvalidClientIdResponse();
                }

                // check if client_secret is valid
                if (!checkClientSecret(oauthRequest.getClientSecret())) {
                    buildInvalidClientSecretResponse();
                }

                // do checking for different grant types
                if (oauthRequest.getParam(OAuth.OAUTH_GRANT_TYPE).equals(GrantType.AUTHORIZATION_CODE.toString())) {
                    if (!checkAuthCode(oauthRequest.getParam(OAuth.OAUTH_CODE))) {
                        buildBadAuthCodeResponse();
                    }
                } else if (oauthRequest.getParam(OAuth.OAUTH_GRANT_TYPE).equals(GrantType.PASSWORD.toString())) {
                    if (!checkUserPass(oauthRequest.getUsername(), oauthRequest.getPassword())) {
                        buildInvalidUserPassResponse();
                    }
                } else if (oauthRequest.getParam(OAuth.OAUTH_GRANT_TYPE).equals(GrantType.REFRESH_TOKEN.toString())) {
                    // refresh token is not supported in this implementation
                    buildInvalidUserPassResponse();
                }

                final String accessToken = oauthIssuerImpl.accessToken();
                CMainServer.getDatabase().addToken(accessToken);

                OAuthResponse response = OAuthASResponse
                        .tokenResponse(HttpServletResponse.SC_OK)
                        .setAccessToken(accessToken)
                        .setExpiresIn("3600")
                        .buildJSONMessage();


                lResponse.setStatus(200);
                lResponse.getWriter().write(response.getBody());
                lResponse.getWriter().flush();
                //lResponse.getWriter().close();

            } catch (OAuthProblemException e) {
                OAuthResponse res = null;
                try {
                    res = OAuthASResponse.errorResponse(HttpServletResponse.SC_BAD_REQUEST).error(e)
                            .buildJSONMessage();
                } catch (OAuthSystemException e1) {
                    e1.printStackTrace();
                }
                lResponse.setStatus(200); //Response.status(res.getResponseStatus()).entity(res.getBody()).build();
                assert res != null;
                lResponse.getWriter().write(res.getBody());
                lResponse.getWriter().flush();
                lResponse.getWriter().close();

            } catch (OAuthSystemException e) {
                e.printStackTrace();
            }

        }
    public void destroy()
    {
        // do nothing.
    }

    private Response buildInvalidClientIdResponse() throws OAuthSystemException {
        OAuthResponse response =
                OAuthASResponse.errorResponse(HttpServletResponse.SC_BAD_REQUEST)
                        .setError(OAuthError.TokenResponse.INVALID_CLIENT)
                        .setErrorDescription(INVALID_CLIENT_DESCRIPTION)
                        .buildJSONMessage();
        return Response.status(response.getResponseStatus()).entity(response.getBody()).build();
    }

    private Response buildInvalidClientSecretResponse() throws OAuthSystemException {
        OAuthResponse response =
                OAuthASResponse.errorResponse(HttpServletResponse.SC_UNAUTHORIZED)
                        .setError(OAuthError.TokenResponse.UNAUTHORIZED_CLIENT).setErrorDescription(INVALID_CLIENT_DESCRIPTION)
                        .buildJSONMessage();
        return Response.status(response.getResponseStatus()).entity(response.getBody()).build();
    }

    private Response buildBadAuthCodeResponse() throws OAuthSystemException {
        OAuthResponse response = OAuthASResponse
                .errorResponse(HttpServletResponse.SC_BAD_REQUEST)
                .setError(OAuthError.TokenResponse.INVALID_GRANT)
                .setErrorDescription("invalid authorization code")
                .buildJSONMessage();
        return Response.status(response.getResponseStatus()).entity(response.getBody()).build();
    }

    private Response buildInvalidUserPassResponse() throws OAuthSystemException {
        OAuthResponse response = OAuthASResponse
                .errorResponse(HttpServletResponse.SC_BAD_REQUEST)
                .setError(OAuthError.TokenResponse.INVALID_GRANT)
                .setErrorDescription("invalid username or password")
                .buildJSONMessage();
        return Response.status(response.getResponseStatus()).entity(response.getBody()).build();
    }

    private boolean checkClientId(String clientId) {
        return true;
    }

    private boolean checkClientSecret(String secret) {
        return true;
    }

    private boolean checkAuthCode(String authCode) {
        //return database.isValidAuthCode(authCode);
        return true;
    }

    private boolean checkUserPass(String user, String pass) {
        //return Common.PASSWORD.equals(pass) && Common.USERNAME.equals(user);
        return true;
    }
}
