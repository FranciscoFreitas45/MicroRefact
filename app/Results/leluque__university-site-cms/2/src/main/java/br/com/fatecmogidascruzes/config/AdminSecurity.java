package br.com.fatecmogidascruzes.config;
 import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.context.SecurityContextPersistenceFilter;
import br.com.fatecmogidascruzes.user.keepconnected.service.KeepConnectedService;
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Order(1)
public class AdminSecurity extends WebSecurityConfigurerAdapter{

 private  KeepConnectedService keepConnected;

 private  UserDetailsService userDetailsService;

 private  SignInReCaptchaFilter signInReCaptchaFilter;

@Autowired
public AdminSecurity(KeepConnectedService keepConnected, UserDetailsService userDetailsService, SignInReCaptchaFilter signInReCaptchaFilter) {
    super();
    this.keepConnected = keepConnected;
    this.userDetailsService = userDetailsService;
    this.signInReCaptchaFilter = signInReCaptchaFilter;
}
@Bean(name = "authenticationManager")
@Override
public AuthenticationManager authenticationManagerBean() throws Exception{
    return super.authenticationManagerBean();
}


@Bean
public PasswordEncoder passwordEncoder(){
    return new BCryptPasswordEncoder();
}


@Autowired
public void configure(AuthenticationManagerBuilder auth) throws Exception{
    auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
}


}