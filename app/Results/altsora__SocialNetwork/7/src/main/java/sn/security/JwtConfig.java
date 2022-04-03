package sn.security;
 import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
@Component
@Getter
public class JwtConfig {

@Value("${security.jwt.header}")
 private  String header;

@Value("${security.jwt.prefix}")
 private  String prefix;

@Value("${security.jwt.expiration}")
 private  int expiration;

@Value("${security.jwt.secret}")
 private  String secret;


}