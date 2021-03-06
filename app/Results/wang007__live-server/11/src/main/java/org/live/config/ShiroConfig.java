package org.live.config;
 import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.session.mgt.SessionValidationScheduler;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.apache.shiro.session.mgt.eis.JavaUuidSessionIdGenerator;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.session.mgt.eis.SessionIdGenerator;
import org.apache.shiro.session.mgt.quartz.QuartzSessionValidationScheduler;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.live.common.shiro.FullShiroFilterFactoryBean;
import org.live.common.shiro.LoginRealm;
import org.live.common.shiro.RetryLimitHashedCredentialsMatcher;
import org.live.config.dataComponent.ShiroDataComponent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.config.MethodInvokingFactoryBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.annotation.Resource;
import java.util.LinkedHashMap;
import java.util.Map;
@Configuration
public class ShiroConfig {

 private  Logger LOGGER;

 public  String AUTHORIZATION_CACHE_KEY;

 public  String AUTHENTICATION_CACHE_KEY;

 public  String ACTIVESESSION_CACHE_KEY;

 private  Map<String,String> filterChainDefinitionMap;

@Resource
 private  ShiroDataComponent shiroData;


@Bean
public FullShiroFilterFactoryBean shiroFilter(){
    FullShiroFilterFactoryBean factoryBean = new FullShiroFilterFactoryBean();
    // ?????????????????????
    factoryBean.setSecurityManager(securityManager());
    if (StringUtils.trimToNull(shiroData.getLoginUrl()) == null) {
        throw new IllegalArgumentException("shiro->loginUrl is null");
    } else {
        LOGGER.info("shiroConfig->shiroData->loginUrl:{}", shiroData.getLoginUrl());
        // ????????????????????????????????????,??????????????????
        // ?????????????????????????????????index.jsp
        factoryBean.setLoginUrl(shiroData.getLoginUrl());
    }
    if (StringUtils.trimToNull(shiroData.getUnauthorizedUrl()) == null) {
        // ???????????????????????????
        LOGGER.warn("?????????shiro??????????????????");
    } else {
        LOGGER.info("shiroConfig->shiroData->unauthorizedUrl:{}", shiroData.getUnauthorizedUrl());
        // ????????????????????????
        factoryBean.setUnauthorizedUrl(shiroData.getUnauthorizedUrl());
    }
    if (StringUtils.trimToNull(shiroData.getSuccessUrl()) == null) {
        LOGGER.warn("?????????shiro??????????????????");
    } else {
        LOGGER.info("shiroConfig->shiroData->successUrl:{}", shiroData.getSuccessUrl());
        // ??????????????????????????????????????????
        factoryBean.setSuccessUrl(shiroData.getSuccessUrl());
    }
    String filterChains = StringUtils.trimToNull(shiroData.getFilterChainDefinitions());
    if (filterChains != null) {
        // ???????????????
        if (filterChains.contains("???"))
            throw new IllegalArgumentException("????????????'???'");
        String[] filters = filterChains.split(",");
        for (String filterChain : filters) {
            String[] urlAndFilter = filterChain.split("=");
            if (urlAndFilter.length != 2)
                throw new IllegalArgumentException("???????????????filter??????????????????:" + filterChain);
            LOGGER.info("shiro->filterchainDefinition--->:{}", filterChain);
            LOGGER.debug("interceptUrl->:{}", urlAndFilter[0]);
            LOGGER.debug("interceptFilter->:{}", urlAndFilter[1]);
            filterChainDefinitionMap.put(urlAndFilter[0], urlAndFilter[1]);
        }
    } else {
        LOGGER.warn("filterChainDefinitions is null");
    }
    // ??????????????????filter???
    factoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
    return factoryBean;
}


@Bean
public SimpleCookie sessionIdCookie(){
    SimpleCookie sessionIdCookie = new SimpleCookie();
    LOGGER.info("shiroConfig->shiroData->cookieName :{}", shiroData.getCookieName());
    // ?????? cookieName
    sessionIdCookie.setName(shiroData.getCookieName());
    sessionIdCookie.setHttpOnly(true);
    // ??????????????????????????????cookie
    sessionIdCookie.setMaxAge(-1);
    return sessionIdCookie;
}


@Bean
@ConditionalOnMissingBean
public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator(){
    DefaultAdvisorAutoProxyCreator daap = new DefaultAdvisorAutoProxyCreator();
    // ??????cglib?????????
    daap.setProxyTargetClass(true);
    return daap;
}


@Bean
public CredentialsMatcher credentialsMatcher(){
    // ?????????????????????
    RetryLimitHashedCredentialsMatcher credentialsMatcher = new RetryLimitHashedCredentialsMatcher(shiroEhCacheManager());
    return credentialsMatcher;
}


@Bean
public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(){
    AuthorizationAttributeSourceAdvisor aasa = new AuthorizationAttributeSourceAdvisor();
    aasa.setSecurityManager(securityManager());
    return aasa;
}


@Bean
public LoginRealm loginRealm(){
    LoginRealm loginRealm = new LoginRealm();
    // ?????????????????????
    loginRealm.setCredentialsMatcher(credentialsMatcher());
    // ????????????
    loginRealm.setCachingEnabled(true);
    // ??????????????????
    loginRealm.setAuthorizationCachingEnabled(true);
    loginRealm.setAuthorizationCacheName(AUTHORIZATION_CACHE_KEY);
    // ??????????????????
    loginRealm.setAuthenticationCachingEnabled(true);
    loginRealm.setAuthenticationCacheName(AUTHENTICATION_CACHE_KEY);
    return loginRealm;
}


@Bean
public SessionDAO sessionDAO(){
    EnterpriseCacheSessionDAO sessionDAO = new EnterpriseCacheSessionDAO();
    // ??????????????????
    sessionDAO.setActiveSessionsCacheName(ACTIVESESSION_CACHE_KEY);
    // ????????????ID?????????
    sessionDAO.setSessionIdGenerator(sessionIdGenerator());
    return sessionDAO;
}


@Bean
public LifecycleBeanPostProcessor lifecycleBeanPostProcessor(){
    LifecycleBeanPostProcessor processor = new LifecycleBeanPostProcessor();
    return processor;
}


@Bean
public SessionIdGenerator sessionIdGenerator(){
    JavaUuidSessionIdGenerator sessionIdGenerator = new JavaUuidSessionIdGenerator();
    return sessionIdGenerator;
}


@Bean
public FormAuthenticationFilter formAuthenticationFilter(){
    FormAuthenticationFilter formAuthenticationFilter = new FormAuthenticationFilter();
    // ??????????????????????????????
    formAuthenticationFilter.setUsernameParam("username");
    // ??????????????????????????????
    formAuthenticationFilter.setPasswordParam("password");
    if (StringUtils.trimToNull(shiroData.getLoginUrl()) == null) {
        throw new IllegalArgumentException("shiro->loginUrl is null");
    } else {
        LOGGER.info("shiroConfig->shiroData->loginUrl:{}", shiroData.getLoginUrl());
        // ????????????????????????????????????,??????????????????
        // ?????????????????????????????????index.jsp
        formAuthenticationFilter.setLoginUrl(shiroData.getLoginUrl());
    }
    return formAuthenticationFilter;
}


@Bean
public EhCacheManager shiroEhCacheManager(){
    EhCacheManager cacheManager = new EhCacheManager();
    cacheManager.setCacheManagerConfigFile("classpath:ehcache.xml");
    return cacheManager;
}


@Bean
public MethodInvokingFactoryBean invokeSetSecurityManager(){
    MethodInvokingFactoryBean bean = new MethodInvokingFactoryBean();
    bean.setStaticMethod("org.apache.shiro.SecurityUtils.setSecurityManager");
    bean.setArguments(new Object[] { securityManager() });
    return bean;
}


@Bean
public ShiroDialect shiroDialect(){
    return new ShiroDialect();
}


@Bean
public SecurityManager securityManager(){
    DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
    // ??????realm
    securityManager.setRealm(loginRealm());
    // ?????????????????????
    securityManager.setSessionManager(sessionManager());
    // ?????????????????????
    securityManager.setCacheManager(shiroEhCacheManager());
    return securityManager;
}


@Bean
public SessionManager sessionManager(){
    DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
    // ??????shiro???????????????
    sessionManager.setGlobalSessionTimeout(shiroData.getGlobalSessionTimeout());
    // ?????????????????????????????????session
    sessionManager.setDeleteInvalidSessions(true);
    // ????????????????????????
    sessionManager.setSessionValidationSchedulerEnabled(true);
    // ??????sessionValidationScheduler???ShiroSpecialConfig?????????
    // shiro???????????????ShiroSpecialConfig?????????
    // ?????????????????????
    sessionManager.setSessionDAO(sessionDAO());
    // ??????SessionIdCookie
    sessionManager.setSessionIdCookieEnabled(true);
    sessionManager.setSessionIdCookie(sessionIdCookie());
    return sessionManager;
}


@Bean
public SessionValidationScheduler sessionValidationScheduler(){
    QuartzSessionValidationScheduler sessionValidationScheduler = new QuartzSessionValidationScheduler();
    // ??????????????????
    sessionValidationScheduler.setSessionValidationInterval(shiroData.getSessionValidationInterval());
    // ??????sessionManager???ShiroSpecialConfig?????????
    return sessionValidationScheduler;
}


}