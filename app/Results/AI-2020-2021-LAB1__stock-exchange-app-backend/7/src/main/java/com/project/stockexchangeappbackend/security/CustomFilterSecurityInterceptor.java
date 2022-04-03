package com.project.stockexchangeappbackend.security;
 import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import javax.servlet;
import java.io.IOException;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.Base64;
import java.util.Map;
@AllArgsConstructor
public class CustomFilterSecurityInterceptor implements Filter{

 private  BannedAccessTokens bannedAccessTokens;

 private  ObjectMapper objectMapper;

 private  Environment env;


@Override
public void doFilter(ServletRequest servletRequest,ServletResponse servletResponse,FilterChain filterChain){
    try {
        String accessToken = ((OAuth2AuthenticationDetails) SecurityContextHolder.getContext().getAuthentication().getDetails()).getTokenValue();
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Base64.Decoder decoder = Base64.getUrlDecoder();
        Map<String, Object> payload = objectMapper.readValue(new String(decoder.decode(accessToken.split("\\.")[1])), Map.class);
        int accessTokenValidity = Integer.parseInt(env.getProperty("security.oauth2.client.access-token-validity-seconds"));
        OffsetDateTime offsetDateTime = OffsetDateTime.ofInstant(Instant.ofEpochSecond((Integer) payload.get("exp")), ZoneId.systemDefault()).minusSeconds(accessTokenValidity);
        if (bannedAccessTokens.isBanned(username, offsetDateTime)) {
            throw new AccessDeniedException("Re-login is required.");
        }
    } catch (JsonProcessingException e) {
        throw new AccessDeniedException("Re-login is required.");
    } catch (ClassCastException e) {
    }
    filterChain.doFilter(servletRequest, servletResponse);
}


}