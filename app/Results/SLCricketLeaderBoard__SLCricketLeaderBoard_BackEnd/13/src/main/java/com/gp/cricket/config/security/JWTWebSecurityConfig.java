package com.gp.cricket.config.security;
 import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class JWTWebSecurityConfig extends WebSecurityConfigurerAdapter{

@Autowired
 private  JwtUnAuthorizedResponseAuthenticationEntryPoint jwtUnAuthorizedResponseAuthenticationEntryPoint;

@Autowired
 private  UserDetailsService jwtInMemoryUserDetailsService;

@Autowired
 private  JwtTokenAuthorizationOncePerRequestFilter jwtAuthenticationTokenFilter;

@Value("${jwt.get.token.uri}")
 private  String authenticationPath;


@Bean
@Override
public AuthenticationManager authenticationManagerBean(){
    return super.authenticationManagerBean();
}


@Bean
public PasswordEncoder passwordEncoderBean(){
    return new BCryptPasswordEncoder();
}


@Autowired
public void configureGlobal(AuthenticationManagerBuilder auth){
    auth.userDetailsService(jwtInMemoryUserDetailsService).passwordEncoder(passwordEncoderBean());
}


@Override
public void configure(WebSecurity webSecurity){
    webSecurity.ignoring().antMatchers(HttpMethod.POST, authenticationPath).antMatchers(HttpMethod.OPTIONS, "/**").and().ignoring().antMatchers(// Other Stuff You want to Ignore
    HttpMethod.GET, // Other Stuff You want to Ignore
    "/").antMatchers(// Other Stuff You want to Ignore
    HttpMethod.GET, // Other Stuff You want to Ignore
    "/playeRanking/**").antMatchers(HttpMethod.GET, "/report/club/**").antMatchers(HttpMethod.GET, "/clubranking/**").antMatchers(HttpMethod.GET, "/get/**");
}


}