package com.evolvingreality.onleave.security;
 import org.apache.commons.lang.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.web.authentication.AbstractAuthenticationTargetUrlRequestHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Component
public class AjaxLogoutSuccessHandler extends AbstractAuthenticationTargetUrlRequestHandlerimplements LogoutSuccessHandler{

 public  String BEARER_AUTHENTICATION;

@Inject
 private  TokenStore tokenStore;


@Override
public void onLogoutSuccess(HttpServletRequest request,HttpServletResponse response,Authentication authentication){
    // Request the token
    String token = request.getHeader("authorization");
    if (token != null && token.startsWith(BEARER_AUTHENTICATION)) {
        final OAuth2AccessToken oAuth2AccessToken = tokenStore.readAccessToken(StringUtils.substringAfter(token, BEARER_AUTHENTICATION));
        if (oAuth2AccessToken != null) {
            tokenStore.removeAccessToken(oAuth2AccessToken);
        }
    }
    response.setStatus(HttpServletResponse.SC_OK);
}


}