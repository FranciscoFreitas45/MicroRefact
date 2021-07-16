import io.jsonwebtoken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import pl.edu.wat.wcy.pz.restaurantServer.security.service.UserPrinciple;
import java.util.Date;
@Component
public class JwtProvider {

 private  Logger LOGGER;

@Value("${authentication.secret.value}")
 private  String jwtSecret;

@Value("${authentication.secret.expiration}")
 private  int jwtExpiration;


public String generateJwtToken(Authentication authentication){
    UserPrinciple principle = (UserPrinciple) authentication.getPrincipal();
    return Jwts.builder().setSubject(principle.getUsername()).setIssuedAt(new Date()).setExpiration(new Date(new Date().getTime() + jwtExpiration * 1000)).signWith(SignatureAlgorithm.HS512, jwtSecret).compact();
}


public boolean validateJwtToken(String authToken){
    try {
        Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
        return true;
    } catch (SignatureException e) {
        LOGGER.error("Signature validation fails.", e);
    } catch (MalformedJwtException e) {
        LOGGER.error("Invalid token", e);
    } catch (ExpiredJwtException e) {
        LOGGER.error("Expired token", e);
    } catch (UnsupportedJwtException e) {
        LOGGER.error("Unsupported token", e);
    } catch (IllegalArgumentException e) {
        LOGGER.error("Empty string", e);
    }
    return false;
}


public String getUsernameFromToken(String authToken){
    return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken).getBody().getSubject();
}


}