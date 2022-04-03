package sn.security;
 import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import sn.repositories.PersonRepository;
import sn.service.AccountService;
import sn.service.JwtUserDetailsService;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import sn.Interface.AccountService;
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityTokenConfig extends WebSecurityConfigurerAdapter{

 private  JwtUserDetailsService userDetailsService;

 private  JwtConfig jwtConfig;

 private  PersonRepository personRepository;

 private  AccountService accountService;

public SecurityTokenConfig(JwtUserDetailsService userDetailsService, JwtConfig jwtConfig, PersonRepository personRepository, @Lazy AccountService accountService) {
    this.userDetailsService = userDetailsService;
    this.jwtConfig = jwtConfig;
    this.personRepository = personRepository;
    this.accountService = accountService;
}
@Bean
public BCryptPasswordEncoder passwordEncoder(){
    return new BCryptPasswordEncoder();
}


@Bean
public CorsConfigurationSource corsConfigurationSource(){
    CorsConfiguration configuration = new CorsConfiguration();
    configuration.setAllowedOrigins(Arrays.asList("*"));
    configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "HEAD"));
    configuration.setAllowedHeaders(Arrays.asList("*"));
    configuration.setAllowCredentials(true);
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", configuration);
    return source;
}


@Override
public void configure(AuthenticationManagerBuilder auth){
    auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
}


@Bean
@RequestScope
public Authentication authentication(){
    return SecurityContextHolder.getContext().getAuthentication();
}


}