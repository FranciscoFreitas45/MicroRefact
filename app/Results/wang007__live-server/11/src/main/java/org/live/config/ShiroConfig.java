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
    // 设置安全管理器
    factoryBean.setSecurityManager(securityManager());
    if (StringUtils.trimToNull(shiroData.getLoginUrl()) == null) {
        throw new IllegalArgumentException("shiro->loginUrl is null");
    } else {
        LOGGER.info("shiroConfig->shiroData->loginUrl:{}", shiroData.getLoginUrl());
        // 验证的失败时，跳转的地址,保存错误信息
        // 设置登录地址，默认值：index.jsp
        factoryBean.setLoginUrl(shiroData.getLoginUrl());
    }
    if (StringUtils.trimToNull(shiroData.getUnauthorizedUrl()) == null) {
        // 未配置未授权的地址
        LOGGER.warn("未配置shiro的未授权地址");
    } else {
        LOGGER.info("shiroConfig->shiroData->unauthorizedUrl:{}", shiroData.getUnauthorizedUrl());
        // 设置未授权的地址
        factoryBean.setUnauthorizedUrl(shiroData.getUnauthorizedUrl());
    }
    if (StringUtils.trimToNull(shiroData.getSuccessUrl()) == null) {
        LOGGER.warn("未配置shiro的未授权地址");
    } else {
        LOGGER.info("shiroConfig->shiroData->successUrl:{}", shiroData.getSuccessUrl());
        // 设置登录成功之后的跳转的地址
        factoryBean.setSuccessUrl(shiroData.getSuccessUrl());
    }
    String filterChains = StringUtils.trimToNull(shiroData.getFilterChainDefinitions());
    if (filterChains != null) {
        // 中文的逗号
        if (filterChains.contains("，"))
            throw new IllegalArgumentException("错误字符'，'");
        String[] filters = filterChains.split(",");
        for (String filterChain : filters) {
            String[] urlAndFilter = filterChain.split("=");
            if (urlAndFilter.length != 2)
                throw new IllegalArgumentException("拦截路径跟filter没有一一对应:" + filterChain);
            LOGGER.info("shiro->filterchainDefinition--->:{}", filterChain);
            LOGGER.debug("interceptUrl->:{}", urlAndFilter[0]);
            LOGGER.debug("interceptFilter->:{}", urlAndFilter[1]);
            filterChainDefinitionMap.put(urlAndFilter[0], urlAndFilter[1]);
        }
    } else {
        LOGGER.warn("filterChainDefinitions is null");
    }
    // 设置访问控制filter链
    factoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
    return factoryBean;
}


@Bean
public SimpleCookie sessionIdCookie(){
    SimpleCookie sessionIdCookie = new SimpleCookie();
    LOGGER.info("shiroConfig->shiroData->cookieName :{}", shiroData.getCookieName());
    // 设置 cookieName
    sessionIdCookie.setName(shiroData.getCookieName());
    sessionIdCookie.setHttpOnly(true);
    // 即，关闭浏览器就失效cookie
    sessionIdCookie.setMaxAge(-1);
    return sessionIdCookie;
}


@Bean
@ConditionalOnMissingBean
public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator(){
    DefaultAdvisorAutoProxyCreator daap = new DefaultAdvisorAutoProxyCreator();
    // 基于cglib的代理
    daap.setProxyTargetClass(true);
    return daap;
}


@Bean
public CredentialsMatcher credentialsMatcher(){
    // 设置缓存管理器
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
    // 设置凭证匹配器
    loginRealm.setCredentialsMatcher(credentialsMatcher());
    // 开启缓存
    loginRealm.setCachingEnabled(true);
    // 开启授权缓存
    loginRealm.setAuthorizationCachingEnabled(true);
    loginRealm.setAuthorizationCacheName(AUTHORIZATION_CACHE_KEY);
    // 开启认证缓存
    loginRealm.setAuthenticationCachingEnabled(true);
    loginRealm.setAuthenticationCacheName(AUTHENTICATION_CACHE_KEY);
    return loginRealm;
}


@Bean
public SessionDAO sessionDAO(){
    EnterpriseCacheSessionDAO sessionDAO = new EnterpriseCacheSessionDAO();
    // 设置缓存名称
    sessionDAO.setActiveSessionsCacheName(ACTIVESESSION_CACHE_KEY);
    // 设置会话ID生成器
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
    // 设置登录表单的账号名
    formAuthenticationFilter.setUsernameParam("username");
    // 设置登录表单的密码名
    formAuthenticationFilter.setPasswordParam("password");
    if (StringUtils.trimToNull(shiroData.getLoginUrl()) == null) {
        throw new IllegalArgumentException("shiro->loginUrl is null");
    } else {
        LOGGER.info("shiroConfig->shiroData->loginUrl:{}", shiroData.getLoginUrl());
        // 验证的失败时，跳转的地址,保存错误信息
        // 设置登录地址，默认值：index.jsp
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
    // 设置realm
    securityManager.setRealm(loginRealm());
    // 设置会话管理器
    securityManager.setSessionManager(sessionManager());
    // 设置缓存管理器
    securityManager.setCacheManager(shiroEhCacheManager());
    return securityManager;
}


@Bean
public SessionManager sessionManager(){
    DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
    // 设置shiro的失效时间
    sessionManager.setGlobalSessionTimeout(shiroData.getGlobalSessionTimeout());
    // 设置是否可以删除失效的session
    sessionManager.setDeleteInvalidSessions(true);
    // 设置开启任务调度
    sessionManager.setSessionValidationSchedulerEnabled(true);
    // 设置sessionValidationScheduler在ShiroSpecialConfig中完成
    // shiro的监听器在ShiroSpecialConfig中完成
    // 设置会话管理器
    sessionManager.setSessionDAO(sessionDAO());
    // 设置SessionIdCookie
    sessionManager.setSessionIdCookieEnabled(true);
    sessionManager.setSessionIdCookie(sessionIdCookie());
    return sessionManager;
}


@Bean
public SessionValidationScheduler sessionValidationScheduler(){
    QuartzSessionValidationScheduler sessionValidationScheduler = new QuartzSessionValidationScheduler();
    // 调度验证周期
    sessionValidationScheduler.setSessionValidationInterval(shiroData.getSessionValidationInterval());
    // 设置sessionManager在ShiroSpecialConfig中完成
    return sessionValidationScheduler;
}


}