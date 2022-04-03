package com.zis.shiro.filter;
 import java.io.Serializable;
import java.util.Deque;
import java.util.LinkedList;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.DefaultSessionKey;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;
import com.zis.shiro.dto.ActiveUser;
public class KickoutSessionControlFilter extends AccessControlFilter{

 private  String kickoutUrl;

 private  boolean kickoutAfter;

 private  int maxSession;

 private  SessionManager sessionManager;

 private  Cache<String,Deque<Serializable>> cache;


public void setSessionManager(SessionManager sessionManager){
    this.sessionManager = sessionManager;
}


public void setKickoutUrl(String kickoutUrl){
    this.kickoutUrl = kickoutUrl;
}


@Override
public boolean isAccessAllowed(ServletRequest request,ServletResponse response,Object mappedValue){
    return false;
}


@Override
public boolean onAccessDenied(ServletRequest request,ServletResponse response){
    Subject subject = getSubject(request, response);
    if (!subject.isAuthenticated() && !subject.isRemembered()) {
        // 如果没有登录，直接进行之后的流程
        return true;
    }
    Session session = subject.getSession();
    ActiveUser au = (ActiveUser) subject.getPrincipal();
    String username = au.getUserName();
    Serializable sessionId = session.getId();
    // 同步控制
    Deque<Serializable> deque = cache.get(username);
    if (deque == null) {
        deque = new LinkedList<Serializable>();
        cache.put(username, deque);
    }
    // 如果队列里没有此sessionId，且用户没有被踢出；放入队列
    if (!deque.contains(sessionId) && session.getAttribute("kickout") == null) {
        deque.push(sessionId);
    }
    // 如果队列里的sessionId数超出最大会话数，开始踢人
    while (deque.size() > maxSession) {
        Serializable kickoutSessionId = null;
        if (kickoutAfter) {
            // 如果踢出后者
            kickoutSessionId = deque.removeFirst();
        } else {
            // 否则踢出前者
            kickoutSessionId = deque.removeLast();
        }
        try {
            Session kickoutSession = sessionManager.getSession(new DefaultSessionKey(kickoutSessionId));
            if (kickoutSession != null) {
                // 设置会话的kickout属性表示踢出了
                kickoutSession.setAttribute("kickout", true);
            }
        } catch (Exception e) {
        // ignore exception
        }
    }
    // 如果被踢出了，直接退出，重定向到踢出后的地址
    if (session.getAttribute("kickout") != null) {
        // 会话被踢出了
        try {
            subject.logout();
        } catch (Exception e) {
        // ignore
        }
        saveRequest(request);
        WebUtils.issueRedirect(request, response, kickoutUrl);
        return false;
    }
    return true;
}


public void setMaxSession(int maxSession){
    this.maxSession = maxSession;
}


public void setKickoutAfter(boolean kickoutAfter){
    this.kickoutAfter = kickoutAfter;
}


public void setCacheManager(CacheManager cacheManager){
    this.cache = cacheManager.getCache("shiro-kickout-session");
}


}