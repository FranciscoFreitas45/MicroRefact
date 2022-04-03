package com.easyshopping.filter;
 import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.easyshopping.Setting;
import com.easyshopping.util.SettingUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;
@Component("siteStatusFilter")
public class SiteStatusFilter extends OncePerRequestFilter{

 private  String[] DEFAULT_IGNORE_URL_PATTERNS;

 private  String DEFAULT_REDIRECT_URL;

 private  AntPathMatcher antPathMatcher;

 private  String[] ignoreUrlPatterns;

 private  String redirectUrl;


@Override
public void doFilterInternal(HttpServletRequest request,HttpServletResponse response,FilterChain filterChain){
    Setting setting = SettingUtils.get();
    if (setting.getIsSiteEnabled()) {
        filterChain.doFilter(request, response);
    } else {
        String path = request.getServletPath();
        if (path.equals(redirectUrl)) {
            filterChain.doFilter(request, response);
            return;
        }
        if (ignoreUrlPatterns != null) {
            for (String ignoreUrlPattern : ignoreUrlPatterns) {
                if (antPathMatcher.match(ignoreUrlPattern, path)) {
                    filterChain.doFilter(request, response);
                    return;
                }
            }
        }
        response.sendRedirect(request.getContextPath() + redirectUrl);
    }
}


public void setIgnoreUrlPatterns(String[] ignoreUrlPatterns){
    this.ignoreUrlPatterns = ignoreUrlPatterns;
}


public String[] getIgnoreUrlPatterns(){
    return ignoreUrlPatterns;
}


public String getRedirectUrl(){
    return redirectUrl;
}


public void setRedirectUrl(String redirectUrl){
    this.redirectUrl = redirectUrl;
}


}