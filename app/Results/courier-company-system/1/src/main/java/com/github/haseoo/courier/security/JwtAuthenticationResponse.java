package com.github.haseoo.courier.security;
 import lombok.Data;
@Data
public class JwtAuthenticationResponse {

 private  String accessToken;

 private  String tokenType;

public JwtAuthenticationResponse(String accessToken) {
    this.accessToken = accessToken;
}
}