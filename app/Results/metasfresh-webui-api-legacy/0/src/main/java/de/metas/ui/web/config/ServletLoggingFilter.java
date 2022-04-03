package de.metas.ui.web.config;
 import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import com.google.common.base.Stopwatch;
import de.metas.logging.LogManager;
import de.metas.ui.web.session.UserSession;
import de.metas.user.UserId;
import de.metas.util.Check;
@Component
public class ServletLoggingFilter implements Filter{

 private  Logger logger;

 private  String MDC_Param_RemoteAddr;

 private  String MDC_Param_RemoteAddr_DefaultValue;

 private  String MDC_Param_LoggedUser;

 private  String MDC_Param_UserAgent;

 private  String MDC_Param_LoggedUserAndRemoteAddr;


public String extractRemoteAddr(HttpServletRequest httpRequest){
    try {
        final String remoteAddr = httpRequest.getRemoteAddr();
        if (remoteAddr == null || remoteAddr.isEmpty()) {
            return MDC_Param_RemoteAddr_DefaultValue;
        }
        return remoteAddr;
    } catch (final Exception e) {
        return "?";
    }
}


@Override
public void init(FilterConfig filterConfig){
}


public String extractUserAgent(HttpServletRequest httpRequest){
    try {
        final String userAgent = httpRequest.getHeader("User-Agent");
        return userAgent;
    } catch (final Exception e) {
        e.printStackTrace();
        return "?";
    }
}


public void updateMDC(ServletRequest request){
    if (!(request instanceof HttpServletRequest)) {
        return;
    }
    final HttpServletRequest httpRequest = (HttpServletRequest) request;
    // 
    // Core MDC parameters
    final String remoteAddr = extractRemoteAddr(httpRequest);
    final String loggedUser = extractLoggedUser(httpRequest);
    final String userAgent = extractUserAgent(httpRequest);
    MDC.put(MDC_Param_RemoteAddr, remoteAddr);
    MDC.put(MDC_Param_LoggedUser, loggedUser);
    MDC.put(MDC_Param_UserAgent, userAgent);
    // 
    // Derivated MDC parameters
    MDC.put(MDC_Param_LoggedUserAndRemoteAddr, extractLoggedUserAndRemoteAddr(loggedUser, remoteAddr));
}


@Override
public void doFilter(ServletRequest request,ServletResponse response,FilterChain chain){
    final Stopwatch stopwatch = Stopwatch.createStarted();
    try {
        updateMDC(request);
        chain.doFilter(request, response);
    } finally {
        // 
        // log the request
        if (logger.isInfoEnabled()) {
            final String requestInfo = extractRequestInfo(request);
            logger.info("Executed in {}: {}", stopwatch.stop(), requestInfo);
        }
        // 
        // Cleanup MDC (keep it last)
        cleanupMDC();
    }
}


@Override
public void destroy(){
}


public void cleanupMDC(){
    MDC.remove(MDC_Param_RemoteAddr);
    MDC.remove(MDC_Param_LoggedUser);
    MDC.remove(MDC_Param_UserAgent);
}


public String extractLoggedUser(HttpServletRequest httpRequest){
    try {
        final UserSession userSession = UserSession.getCurrentOrNull();
        if (userSession == null) {
            return "_noSession";
        }
        if (!userSession.isLoggedIn()) {
            return "_notLoggedIn";
        } else {
            return extractUserName(userSession);
        }
    } catch (final Exception e) {
        e.printStackTrace();
        return "_error";
    }
}


public String extractLoggedUserAndRemoteAddr(String loggedUser,String remoteAddr){
    // NOTE: guard against null parameters, if those at this point they shall not
    return (loggedUser == null ? "?" : loggedUser) + "/" + (remoteAddr == null ? "?" : remoteAddr);
}


public String extractRequestInfo(ServletRequest request){
    if (request instanceof HttpServletRequest) {
        final HttpServletRequest httpRequest = (HttpServletRequest) request;
        final String httpMethod = httpRequest.getMethod();
        final String urlStr = httpRequest.getRequestURL().toString();
        URI uri;
        try {
            uri = new URI(urlStr);
        } catch (final URISyntaxException e) {
            uri = null;
        }
        String path = null;
        if (uri != null) {
            path = uri.getPath();
        }
        if (path == null) {
            path = urlStr;
        }
        final String queryString = httpRequest.getQueryString();
        return (httpMethod != null ? httpMethod : "") + " " + path + (queryString != null ? "?" + queryString : "");
    } else {
        return request.toString();
    }
}


public String extractUserName(UserSession userSession){
    final String userName = userSession.getUserName();
    if (!Check.isEmpty(userName, true)) {
        return userName;
    }
    final UserId loggedUserId = userSession.getLoggedUserIdIfExists().orElse(null);
    if (loggedUserId != null) {
        return String.valueOf(loggedUserId.getRepoId());
    }
    return "?";
}


}