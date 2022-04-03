package com.yalcin.security.jwt;
 import java.io.IOException;
import java.time.LocalDateTime;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.yalcin.entity.Attempt;
import com.yalcin.exception.AccessTokenExpiredException;
import com.yalcin.repository.AttemptRepository;
import com.yalcin.security.services.UserDetailsServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;
import com.yalcin.Interface.UserDetailsServiceImpl;
public class JwtAuthTokenFilter extends OncePerRequestFilter{

@Autowired
 private  JwtProvider tokenProvider;

@Autowired
 private  UserDetailsServiceImpl userDetailsService;

 private  Logger logger;

@Autowired
 private AttemptRepository attemptRepository;


@Override
public void doFilterInternal(HttpServletRequest request,HttpServletResponse response,FilterChain filterChain){
    if (!attemptRepository.existsByIp(request.getRemoteAddr())) {
        Attempt attempt = new Attempt(request.getRemoteAddr(), 0, LocalDateTime.now());
        attemptRepository.save(attempt);
    }
    try {
        String jwt = getJwt(request);
        if (jwt != null && tokenProvider.validateJwtToken(jwt, "authorize", request)) {
            String email = tokenProvider.getSubjectFromJwt(jwt, "authorize");
            UserDetails userDetails = userDetailsService.loadUserByEmail(email);
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
    } catch (Exception e) {
        logger.error("Can NOT set user authentication -> Message: {}", e);
    }
    filterChain.doFilter(request, response);
}


public String getRefreshToken(HttpServletRequest request){
    String authHeader = request.getHeader("Authentication");
    if (authHeader != null && authHeader.startsWith("Bearer ")) {
        return authHeader.replace("Bearer ", "");
    }
    return null;
}


public String getJwt(HttpServletRequest request){
    String authHeader = request.getHeader("Authorization");
    if (authHeader != null && authHeader.startsWith("Bearer ")) {
        return authHeader.replace("Bearer ", "");
    }
    return null;
}


}