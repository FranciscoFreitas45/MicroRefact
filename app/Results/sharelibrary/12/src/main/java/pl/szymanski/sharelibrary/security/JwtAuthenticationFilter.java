package pl.szymanski.sharelibrary.security;
 import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@RequiredArgsConstructor
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter{

 private  JwtTokenProvider tokenProvider;

 private  UserDetailsServiceImpl userDetailsServiceImpl;

 private  Logger logger;


@Override
public void doFilterInternal(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse,FilterChain filterChain){
    String jwt = getJwtFromRequest(httpServletRequest);
    if (StringUtils.hasText(jwt) && tokenProvider.validateToken(jwt)) {
        UserDetails userDetails = userDetailsServiceImpl.loadUserById(tokenProvider.getUserIdFromJWT(jwt));
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    }
    filterChain.doFilter(httpServletRequest, httpServletResponse);
}


public String getJwtFromRequest(HttpServletRequest request){
    String bearerToken = request.getHeader(Constants.AUTH_HEADER);
    if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(Constants.BEARER_TOKEN_BEGIN)) {
        return bearerToken.substring(7);
    }
    return null;
}


}