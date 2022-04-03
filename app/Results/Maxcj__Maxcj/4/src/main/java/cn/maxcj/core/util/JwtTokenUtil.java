package cn.maxcj.core.util;
 import cn.maxcj.core.common.constant.JwtConstants;
import cn.stylefeng.roses.core.util.ToolUtil;
import io.jsonwebtoken;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
public class JwtTokenUtil {


public Date getExpirationDateFromToken(String token){
    return getClaimFromToken(token).getExpiration();
}


public Claims getClaimFromToken(String token){
    return Jwts.parser().setSigningKey(JwtConstants.SECRET).parseClaimsJws(token).getBody();
}


public String generateToken(String userId){
    Map<String, Object> claims = new HashMap<>();
    return doGenerateToken(claims, userId);
}


public String getPrivateClaimFromToken(String token,String key){
    return getClaimFromToken(token).get(key).toString();
}


public void parseToken(String token){
    Jwts.parser().setSigningKey(JwtConstants.SECRET).parseClaimsJws(token).getBody();
}


public String getAudienceFromToken(String token){
    return getClaimFromToken(token).getAudience();
}


public String getUsernameFromToken(String token){
    return getClaimFromToken(token).getSubject();
}


public Date getIssuedAtDateFromToken(String token){
    return getClaimFromToken(token).getIssuedAt();
}


public Boolean isTokenExpired(String token){
    try {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    } catch (ExpiredJwtException expiredJwtException) {
        return true;
    }
}


public String doGenerateToken(Map<String,Object> claims,String subject){
    final Date createdDate = new Date();
    final Date expirationDate = new Date(createdDate.getTime() + JwtConstants.EXPIRATION * 1000);
    return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(createdDate).setExpiration(expirationDate).signWith(SignatureAlgorithm.HS512, JwtConstants.SECRET).compact();
}


public String getRandomKey(){
    return ToolUtil.getRandomString(6);
}


}