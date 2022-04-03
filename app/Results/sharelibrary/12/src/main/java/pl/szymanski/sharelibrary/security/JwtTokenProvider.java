package pl.szymanski.sharelibrary.security;
 import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import java.util.Arrays;
import java.util.Date;
import pl.szymanski.sharelibrary.utilities.Constants.EXCEPTION_TRACK_DELIMITER;
@Component
public class JwtTokenProvider {

 private  Logger logger;

@Value("${app.jwtSecret}")
 private  String jwtSecret;

@Value("${app.jwtExpirationInMs}")
 private  int jwtExpirationInMs;


public String generateToken(Authentication authentication){
    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
    Date now = new Date();
    Date expiryDate = new Date(now.getTime() + jwtExpirationInMs);
    return Jwts.builder().setSubject(Long.toString(userDetails.getId())).setIssuedAt(new Date()).setExpiration(expiryDate).signWith(SignatureAlgorithm.HS512, jwtSecret).compact();
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
        logger.error(String.join(EXCEPTION_TRACK_DELIMITER, Arrays.toString(ex.getStackTrace())));
    }
    return false;
}


}