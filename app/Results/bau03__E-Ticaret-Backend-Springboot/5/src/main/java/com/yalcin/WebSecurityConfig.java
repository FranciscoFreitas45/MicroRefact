package com.yalcin;
 import com.yalcin.security.jwt.JwtAuthEntryPoint;
import com.yalcin.security.jwt.JwtAuthTokenFilter;
import com.yalcin.security.services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import com.yalcin.Interface.UserDetailsServiceImpl;
@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

@Autowired
 private UserDetailsServiceImpl userDetailsService;

@Autowired
 private  JwtAuthEntryPoint unauthorizedHandler;


@Bean
@Override
public AuthenticationManager authenticationManagerBean(){
    return super.authenticationManagerBean();
}


@Bean
public PasswordEncoder passwordEncoder(){
    return new BCryptPasswordEncoder();
}


@Bean
public JwtAuthTokenFilter authenticationJwtTokenFilter(){
    return new JwtAuthTokenFilter();
}


@Override
public void configure(HttpSecurity http){
    http.cors().and().csrf().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and().authorizeRequests().antMatchers("/", "/error", "/favicon.ico", "/**/*.css", "/**/*.js", "/**/*.html", "/**/*.jpg").permitAll().antMatchers("ecommerce/authenticator/**/").permitAll().antMatchers("/api/user/create-new-password/**", "/api/content/category", "/api/content/contents/**", "/api/content/view/**").permitAll();
    http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
}


}