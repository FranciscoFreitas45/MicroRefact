package com.qidian.hcm.common.security;
 import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
@EnableWebSecurity
@Slf4j
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Order(1)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

@Autowired
 private  RestAuthenticationEntryPoint restAuthenticationEntryPoint;

 private  List<String> AUTH_LIST;

 private  List<AntPathRequestMatcher> matchers;


@Bean
public BasicAuthenticationEntryPoint swaggerAuthenticationEntryPoint(){
    BasicAuthenticationEntryPoint entryPoint = new BasicAuthenticationEntryPoint();
    entryPoint.setRealmName("Swagger Realm");
    return entryPoint;
}


@Autowired
public void configureGlobal(AuthenticationManagerBuilder auth){
// auth.inMemoryAuthentication().withUser("admin").password("admin").roles("USER");
}


@Override
@SuppressWarnings("PMD")
public void configure(HttpSecurity http){
    http.formLogin().loginProcessingUrl(// ??????????????????
    "/login").and().antMatcher("/api/**").authorizeRequests().anyRequest().authenticated().and().exceptionHandling().defaultAuthenticationEntryPointFor(swaggerAuthenticationEntryPoint(), new CustomRequestMatcher(AUTH_LIST)).and().httpBasic().authenticationEntryPoint(restAuthenticationEntryPoint).and().csrf().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeRequests().anyRequest().authenticated().and().logout().logoutUrl(// ?????????????????????????????????"/logout"
    "/api/logout").invalidateHttpSession(// ?????????????????????invalidate HttpSession????????????true
    true).deleteCookies("usernameCookie", // ?????????????????????cookies
    "urlCookie");
    // ????????????
    http.headers().cacheControl();
    // ??????JWT filter
    http.addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);
}


@Override
public boolean matches(HttpServletRequest request){
    return matchers.stream().anyMatch(a -> a.matches(request));
}


@Bean
public JwtAuthenticationTokenFilter authenticationTokenFilterBean(){
    return new JwtAuthenticationTokenFilter();
}


}