package com.yalcin.security.jwt;
 import com.yalcin.entity.User;
import com.yalcin.exception.AccessTokenExpiredException;
import com.yalcin.repository.TokenBlacklistRepository;
import com.yalcin.security.services.UserDetailImpl;
import io.jsonwebtoken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import com.yalcin.DTO.UserDetailImpl;
@Component
public class JwtProvider {

 private  Logger logger;

@Autowired
 private TokenBlacklistRepository tokenBlacklistRepository;

@Value("${yalcin.app.jwtSecretForAccessToken}")
 private  String jwtSecretForAccessToken;

@Value("${yalcin.app.jwtAccessTokenExpiration}")
 private  String jwtAccessTokenExpiration;

@Value("${yalcin.app.jwtSecretForRefreshToken}")
 private  String jwtSecretForRefreshToken;

@Value("${yalcin.app.jwtRefreshTokenExpiration}")
 private  String jwtRefreshTokenExpiration;

@Value("${yalcin.app.jwtSecretForVerification}")
 private  String jtwSecretForVerification;

@Value("${yalcin.app.jwtVerificationTokenExpiration}")
 private  String jwtVerificationTokenExpiration;

@Value("${yalcin.app.jtwSecretForPassword}")
 private  String jtwSecretForPassword;

@Value("${yalcin.app.jwtPasswordTokenExpiration}")
 private  String jwtPasswordTokenExpiration;

public JwtProvider() {
}
public String generateJwtTokenForVerification(User user){
    return Jwts.builder().setSubject((user.getEmail())).setIssuedAt(new Date()).setExpiration(new Date((new Date()).getTime() + Long.parseLong(jwtVerificationTokenExpiration))).signWith(SignatureAlgorithm.HS512, jtwSecretForVerification).compact();
}


public Date getIssueDateFromJwt(String token,String matter){
    String secret = getSecret(matter);
    return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getIssuedAt();
}


public String getSubjectFromJwt(String token,String matter){
    String secret = getSecret(matter);
    return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
}


public String getSecret(String matter){
    switch(matter) {
        case "verification":
            return jtwSecretForVerification;
        case "authorize":
            return jwtSecretForAccessToken;
        case "refresh":
            return jwtSecretForRefreshToken;
        case "password":
            return jtwSecretForPassword;
        default:
            return null;
    }
}


public String generateJwtTokenForPassword(String email){
    return Jwts.builder().setSubject(email).setIssuedAt(new Date()).setExpiration(new Date((new Date()).getTime() + Long.parseLong(jwtPasswordTokenExpiration))).signWith(SignatureAlgorithm.HS512, jtwSecretForPassword).compact();
}


public String generateJwtToken(UserDetailImpl userPrincipal){
    Map<String, Object> claims = new HashMap<>();
    return Jwts.builder().setSubject(userPrincipal.getEmail()).signWith(SignatureAlgorithm.HS512, jwtSecretForAccessToken).setIssuedAt(new Date()).setExpiration(new Date((new Date()).getTime() + Long.parseLong(jwtAccessTokenExpiration))).compact();
}


public String generateRefreshToken(Authentication authentication,boolean rememberMe){
    UserDetailImpl userPrincipal = (UserDetailImpl) authentication.getPrincipal();
    if (rememberMe) {
        // 1 year
        this.jwtRefreshTokenExpiration = "31104000000";
    }
    return Jwts.builder().setSubject((userPrincipal.getEmail())).setIssuedAt(new Date()).setExpiration(new Date((new Date()).getTime() + Long.parseLong(jwtRefreshTokenExpiration))).signWith(SignatureAlgorithm.HS512, jwtSecretForRefreshToken).compact();
}


public Date getExpiredDateFromJwt(String token,String matter){
    String secret = getSecret(matter);
    return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getExpiration();
}


public boolean validateJwtToken(String authToken,String matter,HttpServletRequest request){
    String secret = getSecret(matter);
    try {
        if (!matter.equals("verification") && checkExistence(authToken)) {
            return false;
        }
        Jwts.parser().setSigningKey(secret).parseClaimsJws(authToken);
        return true;
    } catch (SignatureException e) {
        logger.error("Invalid JWT signature -> Message: {} ", e);
    } catch (MalformedJwtException e) {
        logger.error("Invalid JWT token -> Message: {}", e);
    } catch (ExpiredJwtException e) {
        request.setAttribute("expired", e.getMessage());
        logger.error("Expired JWT token -> Message: {}");
    } catch (UnsupportedJwtException e) {
        logger.error("Unsupported JWT token -> Message: {}", e);
    } catch (IllegalArgumentException e) {
        logger.error("JWT claims string is empty -> Message: {}", e);
    }
    return false;
}


public boolean checkExistence(String token){
    return tokenBlacklistRepository.existsByToken(token);
}


}