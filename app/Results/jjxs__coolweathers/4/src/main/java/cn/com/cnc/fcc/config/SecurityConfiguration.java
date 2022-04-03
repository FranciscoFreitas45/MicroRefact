package cn.com.cnc.fcc.config;
 import cn.com.cnc.fcc.security;
import cn.com.cnc.fcc.security.jwt;
import cn.com.cnc.fcc.config.DisableUrlSessionFilter;
import org.springframework.beans.factory.BeanInitializationException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpMethod;
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
import org.springframework.web.filter.CorsFilter;
import org.zalando.problem.spring.web.advice.security.SecurityProblemSupport;
import javax.annotation.PostConstruct;
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
@Import(SecurityProblemSupport.class)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{

 private  AuthenticationManagerBuilder authenticationManagerBuilder;

 private  UserDetailsService userDetailsService;

 private  TokenProvider tokenProvider;

 private  CorsFilter corsFilter;

 private  SecurityProblemSupport problemSupport;

 private  DisableUrlSessionFilter disableUrlSessionFilter;

public SecurityConfiguration(AuthenticationManagerBuilder authenticationManagerBuilder, UserDetailsService userDetailsService, TokenProvider tokenProvider, CorsFilter corsFilter, SecurityProblemSupport problemSupport, DisableUrlSessionFilter disableUrlSessionFilter) {
    this.authenticationManagerBuilder = authenticationManagerBuilder;
    this.userDetailsService = userDetailsService;
    this.tokenProvider = tokenProvider;
    this.corsFilter = corsFilter;
    this.problemSupport = problemSupport;
    this.disableUrlSessionFilter = disableUrlSessionFilter;
}
@Bean
public PasswordEncoder passwordEncoder(){
    return new BCryptPasswordEncoder();
}


@PostConstruct
public void init(){
    try {
        authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    } catch (Exception e) {
        throw new BeanInitializationException("Security configuration failed", e);
    }
}


public JWTConfigurer securityConfigurerAdapter(){
    return new JWTConfigurer(tokenProvider);
}


@Override
public void configure(HttpSecurity http){
    http.csrf().disable().addFilterBefore(disableUrlSessionFilter, UsernamePasswordAuthenticationFilter.class).addFilterBefore(corsFilter, UsernamePasswordAuthenticationFilter.class).exceptionHandling().authenticationEntryPoint(problemSupport).accessDeniedHandler(problemSupport).and().headers().frameOptions().disable().and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeRequests().antMatchers("/api/register").permitAll().antMatchers("/api/activate").permitAll().antMatchers("/api/authenticate").permitAll().antMatchers("/api/account/reset-password/init").permitAll().antMatchers("/api/account/reset-password/finish").permitAll().antMatchers("/nrv/mstest").permitAll().antMatchers("/api/**").authenticated().antMatchers("/hst-api/**").hasAuthority(AuthoritiesConstants.PAPI).antMatchers("/papi/gettoken").permitAll().antMatchers("/papi/**").hasAuthority(AuthoritiesConstants.PAPI).antMatchers("/management/health").permitAll().antMatchers("/management/info").permitAll().antMatchers("/management/**").hasAuthority(AuthoritiesConstants.ADMIN).and().apply(securityConfigurerAdapter());
}


}