package co.edu.uniquindio.gri.security;
 import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

 private BCryptPasswordEncoder bCryptPasswordEncoder;

@Autowired
 private UserDetailsServiceImpl userDetailsService;


@Bean
public BCryptPasswordEncoder passwordEncoder(){
    bCryptPasswordEncoder = new BCryptPasswordEncoder(4);
    return bCryptPasswordEncoder;
}


@Autowired
public void configureGlobal(AuthenticationManagerBuilder auth){
    // Permite que el servicio encuentre el usuario en base de datos.
    // Además agrega el encriptador de contraseñas.
    auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
}


@Override
public void configure(HttpSecurity http){
    http.csrf().disable().authorizeRequests().antMatchers("/css/**", "/js/**", "/img/**", "/webjars/**").permitAll().antMatchers("/inventario", "/reporteinventario").access("hasRole('ROLE_ADMIN')").anyRequest().authenticated().and().formLogin().loginPage("/login").permitAll().defaultSuccessUrl("/").failureUrl("/login?error").usernameParameter("username").passwordParameter("password").and().logout().permitAll().logoutSuccessUrl("/login?logout");
}


}