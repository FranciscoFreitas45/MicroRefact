package org.vaadin.paul.spring.app.security;
 import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.vaadin.paul.spring.ui.views.LoginView;
@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{

 private  String LOGIN_PROCESSING_URL;

 private  String LOGIN_FAILURE_URL;

 private  String LOGIN_URL;

 private  String LOGOUT_SUCCESS_URL;


@Bean
@Override
public AuthenticationManager authenticationManagerBean(){
    return super.authenticationManagerBean();
}


@Bean
public CustomRequestCache requestCache(){
    return new CustomRequestCache();
}


@Override
public void configure(WebSecurity web){
    web.ignoring().antMatchers(// Vaadin Flow static resources
    "/VAADIN/**", // the standard favicon URI
    "/favicon.ico", // the robots exclusion standard
    "/robots.txt", // web application manifest
    "/manifest.webmanifest", "/sw.js", "/offline-page.html", // icons and images
    "/icons/**", "/images/**", // (development mode) static resources
    "/frontend/**", // (development mode) webjars
    "/webjars/**", // (development mode) H2 debugging console
    "/h2-console/**", // (production mode) static resources
    "/frontend-es5/**", "/frontend-es6/**");
}


@Bean
public PasswordEncoder encoder(){
    return new BCryptPasswordEncoder(11);
}


}