package com.evolvingreality.onleave.config.OAuth2ServerConfiguration;
 import com.evolvingreality.onleave.security.AjaxLogoutSuccessHandler;
import com.evolvingreality.onleave.security.AuthoritiesConstants;
import com.evolvingreality.onleave.security.Http401UnauthorizedEntryPoint;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import javax.inject.Inject;
import javax.sql.DataSource;
@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter{

@Inject
 private  Http401UnauthorizedEntryPoint authenticationEntryPoint;

@Inject
 private  AjaxLogoutSuccessHandler ajaxLogoutSuccessHandler;


@Override
public void configure(HttpSecurity http){
    http.exceptionHandling().authenticationEntryPoint(authenticationEntryPoint).and().logout().logoutUrl("/api/logout").logoutSuccessHandler(ajaxLogoutSuccessHandler).and().csrf().requireCsrfProtectionMatcher(new AntPathRequestMatcher("/oauth/authorize")).disable().headers().frameOptions().disable().and().authorizeRequests().antMatchers("/api/authenticate").permitAll().antMatchers("/api/register").permitAll().antMatchers("/api/logs/**").hasAnyAuthority(AuthoritiesConstants.ADMIN).antMatchers("/api/**").authenticated().antMatchers("/websocket/tracker").hasAuthority(AuthoritiesConstants.ADMIN).antMatchers("/websocket/**").permitAll().antMatchers("/metrics/**").hasAuthority(AuthoritiesConstants.ADMIN).antMatchers("/health/**").hasAuthority(AuthoritiesConstants.ADMIN).antMatchers("/trace/**").hasAuthority(AuthoritiesConstants.ADMIN).antMatchers("/dump/**").hasAuthority(AuthoritiesConstants.ADMIN).antMatchers("/shutdown/**").hasAuthority(AuthoritiesConstants.ADMIN).antMatchers("/beans/**").hasAuthority(AuthoritiesConstants.ADMIN).antMatchers("/configprops/**").hasAuthority(AuthoritiesConstants.ADMIN).antMatchers("/info/**").hasAuthority(AuthoritiesConstants.ADMIN).antMatchers("/autoconfig/**").hasAuthority(AuthoritiesConstants.ADMIN).antMatchers("/env/**").hasAuthority(AuthoritiesConstants.ADMIN).antMatchers("/trace/**").hasAuthority(AuthoritiesConstants.ADMIN).antMatchers("/api-docs/**").hasAuthority(AuthoritiesConstants.ADMIN).antMatchers("/protected/**").authenticated();
}


}