package com.jepescados.vendasapi.config;
 import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.proxy.NoOp;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import com.jepescados.vendasapi.service.UsuarioService;
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

@Autowired
 private  UsuarioService usuarioService;


@Bean
public PasswordEncoder passwordEncoder(){
    return new BCryptPasswordEncoder();
}


@Bean
public AuthenticationManager authenticationManager(){
    return super.authenticationManager();
}


@Override
public void configure(HttpSecurity http){
    http.csrf().disable().cors().and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
}


}