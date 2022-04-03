package kielce.tu.weaii.telelearn.security;
 import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import kielce.tu.weaii.telelearn.security.Constants.AUTH_COOKIE;
@RequiredArgsConstructor
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter{

 private  JwtTokenProvider tokenProvider;

 private  UserServiceDetailsImpl userDetailsService;


@Override
public void doFilterInternal(HttpServletRequest request,HttpServletResponse response,FilterChain filterChain){
    String jwt = getJwtFromRequest(request);
    if (StringUtils.hasText(jwt) && tokenProvider.validateToken(jwt)) {
        Long userId = tokenProvider.getUserIdFromJWT(jwt);
        UserDetails userDetails = userDetailsService.loadUserById(userId);
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
    filterChain.doFilter(request, response);
}


public String getJwtFromRequest(HttpServletRequest request){
    return request.getCookies() != null ? Arrays.stream(request.getCookies()).filter(c -> c.getName().equals(AUTH_COOKIE)).map(Cookie::getValue).findAny().orElse("") : "";
}


}