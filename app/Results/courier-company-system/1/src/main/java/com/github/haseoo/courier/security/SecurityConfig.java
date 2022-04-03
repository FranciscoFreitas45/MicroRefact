package com.github.haseoo.courier.security;
 import com.github.haseoo.courier.security.oauth2.CustomAuthenticationSuccessHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.client.web.AuthorizationRequestRepository;
import org.springframework.security.oauth2.client.web.HttpSessionOAuth2AuthorizationRequestRepository;
import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.github.haseoo.courier.security.Constants.getUnprotectedEndpoints;
@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, jsr250Enabled = true, prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{

 private  UserDetailsServiceImpl userDetailsService;

 private  JwtAuthenticationEntryPoint unauthorizedHandler;

 private  OidcUserService oidcUserService;

 private  CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;

 private  JwtAuthenticationFilter jwtAuthenticationFilter;


@Bean(BeanIds.AUTHENTICATION_MANAGER)
@Override
public AuthenticationManager authenticationManagerBean(){
    return super.authenticationManagerBean();
}


@Bean
public PasswordEncoder passwordEncoder(){
    return new BCryptPasswordEncoder();
}


@Bean
public AuthorizationRequestRepository<OAuth2AuthorizationRequest> customAuthorizationRequestRepository(){
    return new HttpSessionOAuth2AuthorizationRequestRepository();
}


@Override
public void configure(HttpSecurity http){
    http.cors().and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeRequests().antMatchers(getUnprotectedEndpoints()).permitAll().anyRequest().authenticated().and().exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and().oauth2Login().userInfoEndpoint().oidcUserService(oidcUserService).and().authorizationEndpoint().authorizationRequestRepository(customAuthorizationRequestRepository()).and().successHandler(customAuthenticationSuccessHandler);
    http.csrf().disable();
    http.headers().frameOptions().disable();
    http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
}


}