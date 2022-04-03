package com.qidian.hcm.common.jwt;
 import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class JWTGrantedAuthority implements GrantedAuthority{

 private  String authority;


}