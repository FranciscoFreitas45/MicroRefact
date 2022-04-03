package com.cocay.sicecd;
 import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.cocay.sicecd.service.MyAppUserDetailsService;
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

@Autowired
 private  MyAppUserDetailsService myAppUserDetailsService;


@Autowired
public void configureGlobal(AuthenticationManagerBuilder auth){
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    auth.userDetailsService(myAppUserDetailsService).passwordEncoder(passwordEncoder);
}


@Override
public void configure(HttpSecurity http){
    http.csrf().disable().authorizeRequests().antMatchers("/", "/login", "/logout", "/css/**", "/fonts/**", "/img/**", "/js/**", "/favicon.ico", "/activacion", "/configuracionPass", "/confirmacorreo", "/enviarecupera", "/prueba", "/certificado", "/certificadoRes").permitAll().antMatchers("/AdministracionModificaciones/**").hasAuthority("Administrador").antMatchers("/AdministracionRegistroManual/**").hasAuthority("Administrador").antMatchers("/descargas").authenticated().anyRequest().authenticated().and().formLogin().loginPage("/login").loginProcessingUrl("/login").usernameParameter("username").passwordParameter("password").defaultSuccessUrl("/start").permitAll().and().logout().logoutUrl("/logout").deleteCookies("JSESSIONID").invalidateHttpSession(true).logoutSuccessUrl("/login").permitAll();
}


}