package org.live.common.shiro;
 import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.mgt.FilterChainManager;
import org.apache.shiro.web.filter.mgt.FilterChainResolver;
import org.apache.shiro.web.filter.mgt.PathMatchingFilterChainResolver;
import org.apache.shiro.web.mgt.WebSecurityManager;
import org.apache.shiro.web.servlet.AbstractShiroFilter;
import org.springframework.beans.factory.BeanInitializationException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
public class FullShiroFilterFactoryBean extends ShiroFilterFactoryBean{

 private  Set<String> ignoreExt;

public FullShiroFilterFactoryBean() {
    super();
    ignoreExt = new HashSet<String>();
    ignoreExt.add(".jpg");
    ignoreExt.add(".png");
    ignoreExt.add(".gif");
    ignoreExt.add(".bmp");
    ignoreExt.add(".js");
    ignoreExt.add(".css");
    ignoreExt.add(".ico");
}
@Override
public void doFilterInternal(ServletRequest servletRequest,ServletResponse servletResponse,FilterChain chain) throws ServletException{
    HttpServletRequest request = (HttpServletRequest) servletRequest;
    String str = request.getRequestURI().toLowerCase();
    boolean flag = true;
    int idx = 0;
    if ((idx = str.indexOf(".")) > 0) {
        str = str.substring(idx);
        if (ignoreExt.contains(str))
            flag = false;
    }
    if (flag) {
        super.doFilterInternal(servletRequest, servletResponse, chain);
    } else {
        chain.doFilter(servletRequest, servletResponse);
    }
}


@Override
public AbstractShiroFilter createInstance() throws Exception{
    SecurityManager securityManager = getSecurityManager();
    if (securityManager == null) {
        String msg = "SecurityManager property must be set.";
        throw new BeanInitializationException(msg);
    }
    if (!(securityManager instanceof WebSecurityManager)) {
        String msg = "The security manager does not implement the WebSecurityManager interface.";
        throw new BeanInitializationException(msg);
    }
    FilterChainManager manager = createFilterChainManager();
    PathMatchingFilterChainResolver chainResolver = new PathMatchingFilterChainResolver();
    chainResolver.setFilterChainManager(manager);
    return new SimpleSpringShiroFilter((WebSecurityManager) securityManager, chainResolver);
}


}