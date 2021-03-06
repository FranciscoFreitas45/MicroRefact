package cn.maxcj.config.web;
 import cn.maxcj.core.interceptor.GunsUserFilter;
import cn.maxcj.config.properties.GunsProperties;
import cn.maxcj.core.shiro.ShiroDbRealm;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.Cookie;
import org.apache.shiro.web.servlet.ShiroHttpSession;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.session.mgt.ServletContainerSessionManager;
import org.springframework.beans.factory.config.MethodInvokingFactoryBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import cn.maxcj.DTO.GunsProperties;
@Configuration
public class ShiroConfig {


@Bean
public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(DefaultWebSecurityManager securityManager){
    AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
    authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
    return authorizationAttributeSourceAdvisor;
}


@Bean
public ShiroFilterFactoryBean shiroFilter(DefaultWebSecurityManager securityManager){
    ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
    shiroFilter.setSecurityManager(securityManager);
    /**
     * ?????????????????????url
     */
    shiroFilter.setLoginUrl("/login");
    /**
     * ????????????????????????url
     */
    shiroFilter.setSuccessUrl("/");
    /**
     * ?????????????????????url
     */
    shiroFilter.setUnauthorizedUrl("/global/error");
    /**
     * ???????????????user?????????(???????????????????????????ajax?????? session???????????????,??????????????????????????????????????????)
     */
    HashMap<String, Filter> myFilters = new HashMap<>();
    myFilters.put("user", new GunsUserFilter());
    shiroFilter.setFilters(myFilters);
    /**
     * ??????shiro????????????
     *
     * anon  ???????????????
     * authc ????????????
     * user  ???????????????RememberMe??????????????????
     *
     * ??????????????????rememberMe???,????????????????????????????????????user,????????????authc,??????authc????????????????????????
     *
     * ??????????????????,?????????????????????
     *
     * api?????????????????????rest api???????????????shiro??????
     */
    Map<String, String> hashMap = new LinkedHashMap<>();
    hashMap.put("/static/**", "anon");
    hashMap.put("/gunsApi/**", "anon");
    hashMap.put("/login", "anon");
    hashMap.put("/register", "anon");
    hashMap.put("/goregister", "anon");
    hashMap.put("/index/**", "anon");
    hashMap.put("/kaptcha", "anon");
    hashMap.put("/welcome", "anon");
    hashMap.put("/welcome/**", "anon");
    hashMap.put("/global/sessionError", "anon");
    hashMap.put("/**", "user");
    shiroFilter.setFilterChainDefinitionMap(hashMap);
    return shiroFilter;
}


@Bean
@ConditionalOnProperty(prefix = "guns", name = "spring-session-open", havingValue = "true")
public ServletContainerSessionManager servletContainerSessionManager(){
    return new ServletContainerSessionManager();
}


@Bean
public SimpleCookie rememberMeCookie(){
    SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
    simpleCookie.setHttpOnly(true);
    // 7???
    simpleCookie.setMaxAge(7 * 24 * 60 * 60);
    return simpleCookie;
}


@Bean
public LifecycleBeanPostProcessor lifecycleBeanPostProcessor(){
    return new LifecycleBeanPostProcessor();
}


@Bean
public CacheManager getCacheShiroManager(EhCacheManagerFactoryBean ehcache){
    EhCacheManager ehCacheManager = new EhCacheManager();
    ehCacheManager.setCacheManager(ehcache.getObject());
    return ehCacheManager;
}


@Bean
public ShiroDbRealm shiroDbRealm(){
    return new ShiroDbRealm();
}


@Bean
public DefaultWebSecurityManager securityManager(CookieRememberMeManager rememberMeManager,CacheManager cacheShiroManager,SessionManager sessionManager){
    DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
    securityManager.setRealm(this.shiroDbRealm());
    securityManager.setCacheManager(cacheShiroManager);
    securityManager.setRememberMeManager(rememberMeManager);
    securityManager.setSessionManager(sessionManager);
    return securityManager;
}


@Bean
public CookieRememberMeManager rememberMeManager(SimpleCookie rememberMeCookie){
    CookieRememberMeManager manager = new CookieRememberMeManager();
    manager.setCipherKey(Base64.decode("Z3VucwAAAAAAAAAAAAAAAA=="));
    manager.setCookie(rememberMeCookie);
    return manager;
}


@Bean
@ConditionalOnProperty(prefix = "guns", name = "spring-session-open", havingValue = "false")
public DefaultWebSessionManager defaultWebSessionManager(CacheManager cacheShiroManager,GunsProperties gunsProperties){
    DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
    sessionManager.setCacheManager(cacheShiroManager);
    sessionManager.setSessionValidationInterval(gunsProperties.getSessionValidationInterval() * 1000);
    sessionManager.setGlobalSessionTimeout(gunsProperties.getSessionInvalidateTime() * 1000);
    sessionManager.setDeleteInvalidSessions(true);
    sessionManager.setSessionValidationSchedulerEnabled(true);
    Cookie cookie = new SimpleCookie(ShiroHttpSession.DEFAULT_SESSION_ID_NAME);
    cookie.setName("shiroCookie");
    cookie.setHttpOnly(true);
    sessionManager.setSessionIdCookie(cookie);
    return sessionManager;
}


@Bean
public MethodInvokingFactoryBean methodInvokingFactoryBean(DefaultWebSecurityManager securityManager){
    MethodInvokingFactoryBean bean = new MethodInvokingFactoryBean();
    bean.setStaticMethod("org.apache.shiro.SecurityUtils.setSecurityManager");
    bean.setArguments(new Object[] { securityManager });
    return bean;
}


}