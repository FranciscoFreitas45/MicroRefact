package org.danyuan.application.common.config;
 import java.util.Arrays;
import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
@Configuration
@EnableWebSecurity
// 开启security注解
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

 private  Logger logger;

@Autowired
 private  DataSource dataSource;


@Bean
public PasswordEncoder passwordEncoder(){
    return PasswordEncoderFactories.createDelegatingPasswordEncoder();
}


@Bean
public CorsConfigurationSource corsConfigurationSource(){
    CorsConfiguration configuration = new CorsConfiguration();
    configuration.setAllowedOrigins(Arrays.asList("*"));
    configuration.setAllowedMethods(Arrays.asList("GET", "POST", "OPTIONS", "DELETE"));
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", configuration);
    return source;
}


@Autowired
public void configureGlobal(AuthenticationManagerBuilder auth){
    try {
        auth.userDetailsService(customUserDetailsService()).passwordEncoder(passwordEncoder());
    } catch (Exception e) {
        logger.error(e.getMessage(), WebSecurityConfig.class);
    }
}


@Override
public void configure(HttpSecurity http) throws Exception{
    /**
     * JWT拦截器
     */
    // JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter = new JwtAuthenticationTokenFilter();
    /**
     * 将JWT拦截器添加到UsernamePasswordAuthenticationFilter之前
     */
    // http.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
    // 允许所有用户访问"/"和"/home"
    http.csrf().disable().authorizeRequests().antMatchers("/dist/*/**", "/plugins/*/**", "/pages/*/js/**", "/register.html", "/sysUserBase/save", "/login", "/*/**.md", "/*.pdf").permitAll().anyRequest().authenticated().and().formLogin().loginProcessingUrl("/login").defaultSuccessUrl("/index").loginPage("/login").failureUrl("/login?error").permitAll().and().logout().permitAll().clearAuthentication(true).and().cors().and().rememberMe().tokenRepository(// 设置tokenRepository
    tokenRepository()).alwaysRemember(true).tokenValiditySeconds(1209600);
}


@Bean
public CustomUserDetailsService customUserDetailsService(){
    return new CustomUserDetailsService();
}


@Bean
public PersistentTokenRepository tokenRepository(){
    JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
    jdbcTokenRepository.setDataSource(dataSource);
    // 设置为true,则项目启动时，会在对应数据源中自动建表token表
    jdbcTokenRepository.setCreateTableOnStartup(false);
    return jdbcTokenRepository;
}


}