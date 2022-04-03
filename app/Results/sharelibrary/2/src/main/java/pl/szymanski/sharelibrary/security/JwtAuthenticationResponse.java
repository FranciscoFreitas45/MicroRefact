package pl.szymanski.sharelibrary.security;
 import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
public class JwtAuthenticationResponse {

 private  String accessToken;

 private  String tokenType;

public JwtAuthenticationResponse(String accessToken) {
    this.accessToken = accessToken;
}
}