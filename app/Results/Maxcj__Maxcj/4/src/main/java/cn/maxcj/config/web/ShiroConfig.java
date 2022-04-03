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
     * 默认的登陆访问url
     */
    shiroFilter.setLoginUrl("/login");
    /**
     * 登陆成功后跳转的url
     */
    shiroFilter.setSuccessUrl("/");
    /**
     * 没有权限跳转的url
     */
    shiroFilter.setUnauthorizedUrl("/global/error");
    /**
     * 覆盖默认的user拦截器(默认拦截器解决不了ajax请求 session超时的问题,若有更好的办法请及时反馈作者)
     */
    HashMap<String, Filter> myFilters = new HashMap<>();
    myFilters.put("user", new GunsUserFilter());
    shiroFilter.setFilters(myFilters);
    /**
     * 配置shiro拦截器链
     *
     * anon  不需要认证
     * authc 需要认证
     * user  验证通过或RememberMe登录的都可以
     *
     * 当应用开启了rememberMe时,用户下次访问时可以是一个user,但不会是authc,因为authc是需要重新认证的
     *
     * 顺序从上到下,优先级依次降低
     *
     * api开头的接口，走rest api鉴权，不走shiro鉴权
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
    // 7天
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