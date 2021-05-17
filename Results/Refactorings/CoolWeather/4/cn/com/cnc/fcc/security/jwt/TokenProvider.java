import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import cn.com.cnc.fcc.config.ApplicationProperties;
import cn.com.cnc.fcc.domain.PapiToken;
import cn.com.cnc.fcc.repository.PapiTokenRepository;
import cn.com.cnc.fcc.security.AuthoritiesConstants;
import cn.com.cnc.fcc.service.util.DateUtil;
import cn.com.cnc.fcc.web.rest.errors.PAPIException;
import io.github.jhipster.config.JHipsterProperties;
import io.jsonwebtoken;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
@Component
public class TokenProvider {

 private  Logger log;

 private  String AUTHORITIES_KEY;

 private  Key key;

 private  long tokenValidityInMilliseconds;

 private  long tokenValidityInMillisecondsForRememberMe;

 private  JHipsterProperties jHipsterProperties;

@Autowired
 private  ApplicationProperties applicationProperties;

@Autowired
 private  PapiTokenRepository papiTokenRepository;

@Autowired
 private  DateUtil dateUtil;


@PostConstruct
public void init(){
    byte[] keyBytes;
    String secret = jHipsterProperties.getSecurity().getAuthentication().getJwt().getSecret();
    if (!StringUtils.isEmpty(secret)) {
        log.warn("Warning: the JWT key used is not Base64-encoded. " + "We recommend using the `jhipster.security.authentication.jwt.base64-secret` key for optimum security.");
        keyBytes = secret.getBytes(StandardCharsets.UTF_8);
    } else {
        log.debug("Using a Base64-encoded JWT secret key");
        keyBytes = Decoders.BASE64.decode(jHipsterProperties.getSecurity().getAuthentication().getJwt().getBase64Secret());
    }
    this.key = Keys.hmacShaKeyFor(keyBytes);
    this.tokenValidityInMilliseconds = 1000 * jHipsterProperties.getSecurity().getAuthentication().getJwt().getTokenValidityInSeconds();
    this.tokenValidityInMillisecondsForRememberMe = 1000 * jHipsterProperties.getSecurity().getAuthentication().getJwt().getTokenValidityInSecondsForRememberMe();
}


public Authentication getAuthentication(String token){
    Claims claims = Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
    Collection<? extends GrantedAuthority> authorities = Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(",")).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    User principal = new User(claims.getSubject(), "", authorities);
    return new UsernamePasswordAuthenticationToken(principal, token, authorities);
}


public String createToken(Authentication authentication,boolean rememberMe){
    String authorities = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(","));
    long now = (new Date()).getTime();
    Date validity;
    if (rememberMe) {
        validity = new Date(now + this.tokenValidityInMillisecondsForRememberMe);
    } else {
        validity = new Date(now + this.tokenValidityInMilliseconds);
    }
    String token = Jwts.builder().setSubject(authentication.getName()).claim(AUTHORITIES_KEY, authorities).signWith(key, SignatureAlgorithm.HS512).setExpiration(validity).compact();
    if (AuthoritiesConstants.PAPI.equals(authorities)) {
        PapiToken papiToken = this.papiTokenRepository.findByCode(authentication.getName().toString());
        if (papiToken == null) {
            throw new PAPIException(10001);
        }
        boolean isNow = papiToken.getApiDate() == null ? true : dateUtil.compareByDataFormatterTo(papiToken.getApiDate(), dateUtil.getDBNowDate(), "yyyy-MM-dd");
        if (isNow && papiToken.getApiCount() > applicationProperties.getSecurity().getJwt().getTokenCountDay()) {
            throw new PAPIException(10003);
        }
        papiToken.setApiToken(token);
        papiToken.setApiDate(dateUtil.getDBNowDate());
        if (isNow) {
            papiToken.setApiCount(papiToken.getApiCount() == null ? 1 : papiToken.getApiCount() + 1);
        } else {
            papiToken.setApiCount(1);
        }
        this.papiTokenRepository.save(papiToken);
    }
    return token;
}


public boolean validateToken(String authToken){
    try {
        Claims claims = Jwts.parser().setSigningKey(key).parseClaimsJws(authToken).getBody();
        if (AuthoritiesConstants.PAPI.equals(claims.get(AUTHORITIES_KEY).toString())) {
            Authentication authentication = getAuthentication(authToken);
            PapiToken papiToken = this.papiTokenRepository.findByCode(authentication.getName().toString(), authToken);
            if (papiToken == null) {
                throw new PAPIException(10002);
            }
        }
        Jwts.parser().setSigningKey(key).parseClaimsJws(authToken);
        return true;
    } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
        log.info("Invalid JWT signature.");
        log.trace("Invalid JWT signature trace: {}", e);
    } catch (ExpiredJwtException e) {
        log.info("Expired JWT token.");
        log.trace("Expired JWT token trace: {}", e);
    } catch (UnsupportedJwtException e) {
        log.info("Unsupported JWT token.");
        log.trace("Unsupported JWT token trace: {}", e);
    } catch (IllegalArgumentException e) {
        log.info("JWT token compact of handler are invalid.");
        log.trace("JWT token compact of handler are invalid trace: {}", e);
    }
    return false;
}


}