package pl.szymanski.sharelibrary.security;
 import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
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
import pl.szymanski.sharelibrary.security.Constants.getUnprotectedEndpoints;
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, jsr250Enabled = true, prePostEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter{

 private  UserDetailsService userDetailsService;

 private  JwtAuthenticationEntryPoint unauthorizedHandler;

 private  JwtAuthenticationFilter jwtAuthenticationFilter;


@Bean
public PasswordEncoder passwordEncoder(){
    return new BCryptPasswordEncoder();
}


@Override
@Bean(BeanIds.AUTHENTICATION_MANAGER)
public AuthenticationManager authenticationManagerBean(){
    return super.authenticationManagerBean();
}


@Override
public void configure(HttpSecurity http){
    http.cors().and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeRequests().antMatchers(getUnprotectedEndpoints()).permitAll().anyRequest().authenticated().and().exceptionHandling().authenticationEntryPoint(unauthorizedHandler);
    http.csrf().disable();
    http.headers().frameOptions().disable();
    http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
}


}