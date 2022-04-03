package kielce.tu.weaii.telelearn.security;
 import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import kielce.tu.weaii.telelearn.models.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import java.util.Arrays;
import java.util.Date;
@Slf4j
@Component
public class JwtTokenProvider {

@Value("${kielce.tu.weaii.telelearn.security.secret}")
 private  String jwtSecret;

@Value("${kielce.tu.weaii.telelearn.security.jwtExpirationMs}")
 private  int jwtExpirationTime;


public String generateToken(Authentication authentication){
    User userPrincipal = (User) authentication.getPrincipal();
    Date expiryDate = new Date(new Date().getTime() + jwtExpirationTime);
    return Jwts.builder().setSubject(Long.toString(userPrincipal.getId())).setIssuedAt(new Date()).setExpiration(expiryDate).signWith(SignatureAlgorithm.HS512, jwtSecret).compact();
}


public Long getUserIdFromJWT(String token){
    Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
    return Long.parseLong(claims.getSubject());
}


public boolean validateToken(String authToken){
    try {
        Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
        return true;
    } catch (JwtException ex) {
        log.error(String.join("\n", Arrays.toString(ex.getStackTrace())));
    }
    return false;
}


}