package com.evolvingreality.onleave.config;
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
public class OAuth2ServerConfiguration {

@Inject
 private  Http401UnauthorizedEntryPoint authenticationEntryPoint;

@Inject
 private  AjaxLogoutSuccessHandler ajaxLogoutSuccessHandler;

 private  String ENV_OAUTH;

 private  String PROP_CLIENTID;

 private  String PROP_SECRET;

 private  String PROP_TOKEN_VALIDITY_SECONDS;

 private  RelaxedPropertyResolver propertyResolver;

@Inject
 private  DataSource dataSource;

@Inject
@Qualifier("authenticationManagerBean")
 private  AuthenticationManager authenticationManager;


@Override
public void configure(ClientDetailsServiceConfigurer clients){
    clients.inMemory().withClient(propertyResolver.getProperty(PROP_CLIENTID)).scopes("read", "write").authorities(AuthoritiesConstants.ADMIN, AuthoritiesConstants.USER).authorizedGrantTypes("password", "refresh_token").secret(propertyResolver.getProperty(PROP_SECRET)).accessTokenValiditySeconds(propertyResolver.getProperty(PROP_TOKEN_VALIDITY_SECONDS, Integer.class, 1800));
}


@Bean
public TokenStore tokenStore(){
    return new JdbcTokenStore(dataSource);
}


@Override
public void setEnvironment(Environment environment){
    this.propertyResolver = new RelaxedPropertyResolver(environment, ENV_OAUTH);
}


}