package com.netease.config;
 import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.servlet.Filter;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authc.AnonymousFilter;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.filter.authc.LogoutFilter;
import org.apache.shiro.web.filter.authc.UserFilter;
import org.apache.shiro.web.filter.authz.PermissionsAuthorizationFilter;
import org.apache.shiro.web.filter.authz.RolesAuthorizationFilter;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.mgt.WebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.MethodInvokingFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import com.netease.security.UserRealm;
@Configuration
public class SecurityConfig {


@Bean
@Autowired
public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(WebSecurityManager securityManager){
    AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
    authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
    return authorizationAttributeSourceAdvisor;
}


@Bean
@Autowired
public ShiroFilterFactoryBean shiroFilter(WebSecurityManager securityManager){
    ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
    shiroFilter.setLoginUrl("/Credit/userLogin");
    // shiroFilter.setSuccessUrl("/neteaseCredit/success");
    // shiroFilter.setLoginUrl("/admin/login");
    // shiroFilter.setSuccessUrl("/admin/");
    shiroFilter.setSuccessUrl("/neteaseCredit/success");
    Map<String, String> definitionsMap = new LinkedHashMap<>();
    definitionsMap.put("/admin/login", "anon");
    definitionsMap.put("/admin/list/**", "authc,roles[god]");
    // 添加用户
    definitionsMap.put("/admin/add", "authc,roles[admin]");
    // 修改用户基本信息
    definitionsMap.put("/admin/update", "authc,roles[admin]");
    // 修改用户密码
    definitionsMap.put("/admin/reset", "authc,roles[admin,god]");
    // 删除用户
    definitionsMap.put("/admin/delete", "authc,roles[admin]");
    // 登陆页面认证
    definitionsMap.put("/admin/**", "authc,roles[admin]");
    // 征信，模块权限设置
    definitionsMap.put("/Credit/login", "anon");
    definitionsMap.put("/credit/**", "anon");
    shiroFilter.setFilterChainDefinitionMap(definitionsMap);
    Map<String, Filter> filters = new HashMap<>();
    filters.put("anon", new AnonymousFilter());
    filters.put("authc", new FormAuthenticationFilter());
    filters.put("logout", new LogoutFilter());
    filters.put("roles", new RolesAuthorizationFilter());
    filters.put("user", new UserFilter());
    filters.put("perms", new PermissionsAuthorizationFilter());
    shiroFilter.setFilters(filters);
    shiroFilter.setSecurityManager(securityManager);
    return shiroFilter;
}


@Bean
public LifecycleBeanPostProcessor lifecycleBeanPostProcessor(){
    return new LifecycleBeanPostProcessor();
}


@Bean
@DependsOn(value = "lifecycleBeanPostProcessor")
public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator(){
    return new DefaultAdvisorAutoProxyCreator();
}


@Bean
@Autowired
public UserRealm userRealm(CredentialsMatcher credentialsMatcher){
    UserRealm realm = new UserRealm();
    realm.setCredentialsMatcher(credentialsMatcher);
    return realm;
}


@Bean
@Autowired
public WebSecurityManager securityManager(UserRealm userRealm){
    DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
    securityManager.setRealm(userRealm);
    return securityManager;
}


@Bean
public CredentialsMatcher credentialsMatcher(){
    HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
    matcher.setHashAlgorithmName("SHA-256");
    matcher.setHashIterations(1);
    matcher.setStoredCredentialsHexEncoded(true);
    return matcher;
}


@Bean
@Autowired
public MethodInvokingFactoryBean methodInvokingFactoryBean(WebSecurityManager securityManager){
    MethodInvokingFactoryBean methodInvokingFactoryBean = new MethodInvokingFactoryBean();
    methodInvokingFactoryBean.setStaticMethod("org.apache.shiro.SecurityUtils.setSecurityManager");
    methodInvokingFactoryBean.setArguments(new Object[] { securityManager });
    return methodInvokingFactoryBean;
}


}