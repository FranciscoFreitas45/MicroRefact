package com.weflors.config;
 import com.weflors.entity.UserEntity;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.weflors.service.UserDetailsServiceImpl;
// Данная аннотация говорит  Spring что он является классом конфигурации, поэтому он будет анализирован с помощью  Spring во время запуска данного приложения.
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

@Autowired
 private  UserDetailsServiceImpl userDetailsService;

@Autowired
 private  DataSource dataSource;


@Bean
public BCryptPasswordEncoder passwordEncoder(){
    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    return bCryptPasswordEncoder;
}


@Autowired
public void configureGlobal(AuthenticationManagerBuilder auth){
    // Setting Service to find User in the database.
    // And Setting PassswordEncoder
    auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
}


@Bean
public GrantedAuthorityDefaults grantedAuthorityDefaults(){
    // Remove the "ROLE_" prefix
    return new GrantedAuthorityDefaults("");
}


@Override
public void configure(HttpSecurity http){
    http.csrf().disable();
    /*			http.sessionManagement().sessionCreationPolicy(
					SessionCreationPolicy.STATELESS);*/
    // The pages does not require login
    http.authorizeRequests().antMatchers("/", "/login", "/logout", "/registration").permitAll();
    // /hello(любая другая после админа) page requires login as ROLE_USER or ROLE_ADMIN.
    // If no login, it will redirect to /login page.
    // TO_DO
    // http.authorizeRequests().antMatchers("/hello").access("hasAnyRole('user', 'admin')");
    // For ADMIN only.
    // TO_DO
    http.authorizeRequests().antMatchers("/users", "/contragents/add", "/contragents/update", "/contragents/delete", "/clients/add", "/clients/update", "/clients/delete", "/typeproduct/add", "/typeproduct/update", "/typeproduct/delete", "/salesreport", "writeoffsreport").access("hasRole('admin')");
    http.authorizeRequests().antMatchers("/hello").access("hasRole('user')");
    // When the user has logged in as XX.
    // But access a page that requires role YY,
    // AccessDeniedException will be thrown.
    // http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/403");
    // Config for Login Form
    // 
    http.authorizeRequests().and().formLogin().loginProcessingUrl(// Submit URL
    "/j_spring_security_check").loginPage(// 
    "/login").defaultSuccessUrl(// 
    "/main").failureUrl(// 
    "/login?error=true").usernameParameter(// 
    "j_username").passwordParameter("j_password").and().logout().logoutUrl("/logout").logoutSuccessUrl("/login").clearAuthentication(true).invalidateHttpSession(true).permitAll();
}


}