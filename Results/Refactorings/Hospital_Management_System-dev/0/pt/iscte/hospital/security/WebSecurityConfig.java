import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

@Autowired
 private  CustomAuthenticationProvider authProvider;


@Bean
public PasswordEncoder passwordEncoder(){
    return new BCryptPasswordEncoder();
}


@Override
public void configure(HttpSecurity http){
    // http builder configurations for authorize requests and form login (see below)
    http.csrf().disable().authorizeRequests().antMatchers("/").permitAll().antMatchers("/test/**").permitAll().antMatchers("/patient/**").hasRole("PATIENT").antMatchers("/employee/**").hasRole("EMPLOYEE").antMatchers("/receptionist/**").hasRole("RECEPTIONIST").antMatchers("/doctor/**").hasRole("DOCTOR").antMatchers("/unitresponsible/**").hasRole("UNIT_RESPONSIBLE").antMatchers("/doctor-receptionist/**").hasAnyRole("DOCTOR", "RECEPTIONIST").antMatchers("/patient-receptionist/**").hasAnyRole("PATIENT", "RECEPTIONIST").antMatchers("/user/**", "/main", "/userToMain").hasAnyRole("PATIENT", "EMPLOYEE", "RECEPTIONIST", "DOCTOR", "UNIT_RESPONSIBLE").antMatchers("/public/**").permitAll().antMatchers("/*.css", "/fonts/**", "/imagens/**", "*.html", "*.ico").permitAll().antMatchers("/uploaded_images/**").hasAnyRole("PATIENT", "EMPLOYEE", "RECEPTIONIST", "DOCTOR", "UNIT_RESPONSIBLE").anyRequest().authenticated().and().formLogin().loginPage("/public/login").defaultSuccessUrl("/", true).failureUrl("/public/login?error=true").and().logout().logoutUrl(// Este método faz logout do user
    "/logout").logoutSuccessUrl("/public/main").deleteCookies("JSESSIONID");
// ...
}


}