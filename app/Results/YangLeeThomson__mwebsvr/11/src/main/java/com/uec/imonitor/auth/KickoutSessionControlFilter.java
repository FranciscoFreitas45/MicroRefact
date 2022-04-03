package com.uec.imonitor.auth;
 import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.DefaultSessionKey;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;
import com.alibaba.fastjson.JSON;
import com.mysql.fabric.xmlrpc.base.Data;
import com.uec.imonitor.common.util.CommonUtil;
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
    String username = (String) subject.getPrincipal();
    Serializable sessionId = session.getId();
    // 读取缓存   没有就存入
    Deque<Serializable> deque = cache.get(username);
    // 如果此用户没有session队列，也就是还没有登录过，缓存中没有
    // 就new一个空队列，不然deque对象为空，会报空指针
    if (deque == null) {
        deque = new LinkedList<Serializable>();
    }
    // 如果队列里没有此sessionId，且用户没有被踢出；放入队列
    if (!deque.contains(sessionId) && session.getAttribute("kickout") == null) {
        // 将sessionId存入队列
        deque.push(sessionId);
        // 将用户的sessionId队列缓存
        cache.put(username, deque);
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
        // 踢出后再更新下缓存队列
        cache.put(username, deque);
        try {
            // 获取被踢出的sessionId的session对象
            Session kickoutSession = sessionManager.getSession(new DefaultSessionKey(kickoutSessionId));
            if (kickoutSession != null) {
                // 设置会话的kickout属性表示踢出了
                kickoutSession.setAttribute("kickout", true);
                kickoutSession.setAttribute("ip", CommonUtil.getIp((HttpServletRequest) request));
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                kickoutSession.setAttribute("kickoutTime", sdf.format(new Date()));
            }
        } catch (Exception e) {
        // ignore exception
        }
    }
    // 如果被踢出了，直接退出，重定向到踢出后的地址
    if ((Boolean) session.getAttribute("kickout") != null && (Boolean) session.getAttribute("kickout") == true) {
        // saveRequest(request);
        String ip = (String) session.getAttribute("ip");
        String time = (String) session.getAttribute("kickoutTime");
        // 会话被踢出了
        try {
            // 退出登录
            subject.logout();
        } catch (Exception e) {
        // ignore
        }
        Map<String, Object> resultMap = new HashMap<String, Object>();
        // 判断是不是Ajax请求
        if ("XMLHttpRequest".equalsIgnoreCase(((HttpServletRequest) request).getHeader("X-Requested-With"))) {
            // resultMap.put("user_status", "300");
            // resultMap.put("message", "您已经在其他地方登录，请重新登录！");
            // 输出json串
            // out(response, resultMap);
            resultMap.put("result", false);
            resultMap.put("errorCode", 401);
            resultMap.put("errorMsg", "您的账号在另一地点登录，您被迫下线。");
            Map<String, String> map = new HashMap<String, String>();
            map.put("ip", ip);
            map.put("time", time);
            resultMap.put("resultObj", map);
            out(response, resultMap);
        } else {
            // 重定向
            WebUtils.issueRedirect(request, response, kickoutUrl + "?ip=" + ip + "&time=" + time);
        }
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
    this.cache = cacheManager.getCache("shiroCache");
}


public void out(ServletResponse hresponse,Map<String,Object> resultMap){
    try {
        hresponse.setCharacterEncoding("UTF-8");
        PrintWriter out = hresponse.getWriter();
        out.println(JSON.toJSONString(resultMap));
        out.flush();
        out.close();
    } catch (Exception e) {
    // System.err.println("KickoutSessionFilter.class 输出JSON异常，可以忽略。");
    }
}


}