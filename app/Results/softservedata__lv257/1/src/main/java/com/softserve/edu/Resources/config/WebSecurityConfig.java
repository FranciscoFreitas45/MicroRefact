package com.softserve.edu.Resources.config;
 import com.softserve.edu.Resources.authentication.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
@Configuration
@EnableWebSecurity
@Transactional
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

@Autowired
 private  MyUserDetailsService myUserDetailsService;


@Autowired
public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
    // Users in memory. uncomment to try
    /*auth.inMemoryAuthentication().withUser("user1").password("12345").roles("USER");
        auth.inMemoryAuthentication().withUser("admin1").password("12345").roles("USER, ADMIN");*/
    // For User in database.
    auth.userDetailsService(myUserDetailsService).passwordEncoder(encoder());
}


@Override
public void configure(HttpSecurity http) throws Exception{
    http.csrf().disable();
    // The pages does not require login
    http.authorizeRequests().antMatchers("/", "/welcome", "/login", "/logout").permitAll();
    // /userInfo page requires login as USER or ADMIN.
    // If no login, it will redirect to /login page.
    http.authorizeRequests().antMatchers("/userInfo").access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')");
    // For ADMIN only.
    http.authorizeRequests().antMatchers("/admin", "/users", "/roles", "/privileges").access("hasRole('ROLE_ADMIN')");
    // Register resource only for REGISTRATOR
    http.authorizeRequests().antMatchers("/resources/registration").access("hasRole('ROLE_REGISTRATOR')");
    http.authorizeRequests().antMatchers("/resources/requests", "/resources/history", "/resources/addType", "/resources/editType", "/resources/viewType").access("hasRole('ROLE_RESOURCE_ADMIN')");
    // When the user has logged in as XX.
    // But access a page that requires role YY,
    // AccessDeniedException will throw.
    http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/403");
    // Config for Login Form
    // 
    http.authorizeRequests().and().formLogin().loginProcessingUrl(// Submit URL
    "/j_spring_security_check").loginPage(// 
    "/login").defaultSuccessUrl(// 
    "/").failureUrl(// 
    "/login?error=true").usernameParameter(// 
    "username").passwordParameter("password").and().logout().logoutUrl("/logout").logoutSuccessUrl("/logoutSuccessful");
}


@Bean
public PasswordEncoder encoder(){
    return new BCryptPasswordEncoder(11);
}


}