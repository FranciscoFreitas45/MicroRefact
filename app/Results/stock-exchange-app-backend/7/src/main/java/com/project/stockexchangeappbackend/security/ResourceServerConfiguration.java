package com.project.stockexchangeappbackend.security;
 import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
@Configuration
@EnableResourceServer
@AllArgsConstructor
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter{

 private  BannedAccessTokens bannedAccessTokens;

 private  ObjectMapper objectMapper;

 private  Environment env;


@Override
public void configure(HttpSecurity http){
    http.csrf().disable().authorizeRequests().antMatchers("/api/**").permitAll().and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    http.addFilterAfter(new CustomFilterSecurityInterceptor(bannedAccessTokens, objectMapper, env), FilterSecurityInterceptor.class);
}


}