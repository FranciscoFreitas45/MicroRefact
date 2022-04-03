package switchtwentytwenty.project.autentication;
 import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import io.jsonwebtoken;
import org.springframework.stereotype.Component;
@Component
public class JwtUtils {

 private  Logger logger;

@Value("${switch.g2.app.jwtSecret}")
 private  String jwtSecret;

@Value("${switch.g2.app.jwtExpirationMs}")
 private  int jwtExpirationMs;


public String getUserNameFromJwtToken(String token){
    return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
}


public String generateJwtToken(Authentication authentication){
    UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();
    return // o token envolve isto tudo
    Jwts.builder().setSubject((userPrincipal.getUsername())).setIssuedAt(new Date()).setExpiration(// valores descritos na 16 e 19
    new Date((new Date()).getTime() + jwtExpirationMs)).signWith(SignatureAlgorithm.HS512, // codifica o secret
    jwtSecret).compact();
}


public boolean validateJwtToken(String authToken){
    try {
        Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
        return true;
    } catch (SignatureException e) {
        logger.error("Invalid JWT signature: {}", e.getMessage());
    } catch (MalformedJwtException e) {
        logger.error("Invalid JWT token: {}", e.getMessage());
    } catch (ExpiredJwtException e) {
        logger.error("JWT token is expired: {}", e.getMessage());
    } catch (UnsupportedJwtException e) {
        logger.error("JWT token is unsupported: {}", e.getMessage());
    } catch (IllegalArgumentException e) {
        logger.error("JWT claims string is empty: {}", e.getMessage());
    }
    return false;
}


}