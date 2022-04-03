package switchtwentytwenty.project.autentication;
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
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(// securedEnabled = true,
// jsr250Enabled = true,
prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{

@Autowired
 private UserDetailsServiceImpl userDetailsService;

@Autowired
 private  AuthEntryPointJwt unauthorizedHandler;


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
public AuthTokenFilter authenticationJwtTokenFilter(){
    return new AuthTokenFilter();
}


@Override
public void configure(HttpSecurity http){
    http.cors().and().csrf().disable().exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeRequests().antMatchers("/auth/**").permitAll().antMatchers("/").permitAll().antMatchers(HttpMethod.POST, "/categories/standard").access("hasRole('ROLE_SYSTEM_MANAGER')").antMatchers(HttpMethod.GET, "/categories/standard").permitAll().antMatchers("/categories/standard/tree").permitAll().antMatchers(HttpMethod.POST, "/families").access("hasRole('ROLE_SYSTEM_MANAGER')").antMatchers(HttpMethod.GET, "/families").access("hasRole('ROLE_SYSTEM_MANAGER')").antMatchers(HttpMethod.POST, "/users").access("hasRole('ROLE_ADMIN')").antMatchers(HttpMethod.GET, "/families/{familyID}/users").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')").antMatchers(HttpMethod.GET, "/families/relations/{id}").permitAll().antMatchers(HttpMethod.POST, "/families/{familyID}/relations").access("hasRole('ROLE_ADMIN')").antMatchers(HttpMethod.GET, "/families/relations").permitAll().antMatchers(HttpMethod.POST, "/families/{familyID}/familyCashAccount").access("hasRole('ROLE_ADMIN')").antMatchers(HttpMethod.GET, "/users/{id}").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')").antMatchers(HttpMethod.POST, "/users/{personId}/email").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')").antMatchers(HttpMethod.DELETE, "/users/{personId}/email").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')").antMatchers("/accounts/{accountID}/transfer/{accountID}").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')").antMatchers(HttpMethod.POST, "/users/{personId}/accounts/{accountId}/balance").access("hasRole('ROLE_ADMIN')").antMatchers(HttpMethod.POST, "/users/{personId}/personalCashAccount").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')").antMatchers(HttpMethod.POST, "/accounts/bank").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')").antMatchers(HttpMethod.POST, "/accounts/{accountID}/payment").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')").antMatchers("/accounts/{accountID}").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')").antMatchers("/accounts/{accountID}/movements").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')").antMatchers(HttpMethod.GET, "/families/{familyID}").permitAll().antMatchers(HttpMethod.OPTIONS, "/accounts").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')").antMatchers(HttpMethod.GET, "/users/{personID}/ledger").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')").antMatchers(HttpMethod.GET, "/families/{familyID}/ledger").access("hasRole('ROLE_ADMIN')").antMatchers("/h2-console/**").permitAll().anyRequest().authenticated();
    http.headers().frameOptions().disable();
    http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
}


}