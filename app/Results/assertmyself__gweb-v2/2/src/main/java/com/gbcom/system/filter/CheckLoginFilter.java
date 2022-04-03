package com.gbcom.system.filter;
 import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import com.gbcom.system.utils.ServerContext;
public class CheckLoginFilter implements Filter{

 private  Logger LOG;

 protected  FilterConfig filterConfig;

 private  String redirectURL;

 private  String urlRegx;

 private  List<String> notCheckURLList;

 private  String sessionKey;


public void init(FilterConfig filterConfig){
    this.filterConfig = filterConfig;
    redirectURL = filterConfig.getInitParameter("redirectURL");
    sessionKey = filterConfig.getInitParameter("checkSessionKey");
    String notCheckURLListStr = filterConfig.getInitParameter("notCheckURLList");
    if (notCheckURLListStr != null) {
        StringTokenizer st = new StringTokenizer(notCheckURLListStr, ";");
        notCheckURLList.clear();
        while (st.hasMoreTokens()) {
            notCheckURLList.add(st.nextToken());
        }
    }
}


public void doFilter(ServletRequest servletRequest,ServletResponse servletResponse,FilterChain filterChain){
    HttpServletRequest request = (HttpServletRequest) servletRequest;
    HttpServletResponse response = (HttpServletResponse) servletResponse;
    // 每次请求会将request对象 保存到threadlocal中。
    if (LOG.isDebugEnabled()) {
        LOG.info("/---------  HTTP-Thread --------- ");
        LOG.info("thread id = " + Thread.currentThread());
        LOG.info("request  hashcode=" + request.hashCode() + " ; request URL=" + request.getRequestURI());
        LOG.info("SESSION  session hashcode=" + request.getSession().hashCode() + " ; session ID=" + request.getSession().getId());
        LOG.info("/---------  HTTP-Thread --------- ");
    }
    // spring is not need ,,user process need; login.jsp?error=expired
    ServerContext.setRequest(request);
    ServerContext.setResponse(response);
    // HttpSession session = request.getSession();
    /*		 boolean flag =false;
        if(session==null ||null == session.getAttribute("domain")){
            for(String str : notCheckURLList){
                if(request.getRequestURI().indexOf(str)!=-1){
                    flag = true;
                    break;
                }
            }
            if(!flag){
            	LOG.info("session （domain discard） time out!!!!URL =" +request.getRequestURI()+";;redirectURL="+redirectURL);
            	response.sendRedirect(request.getContextPath() + redirectURL);
            	return;
            }
        }*/
    /*
		if (request.getRequestURI().contains(urlRegx)) {
			// 加密狗线程包括对session的判断
			HttpSession session = request.getSession();
			// 用户超时或没有登陆时跳转到登陆页面
			if (session==null || session.getAttribute("domain") == null) {
				LOG.info("session time out!!!!");
				response.sendRedirect(request.getContextPath() + redirectURL);
			} else {
				request.getSession().setAttribute("domain",
						request.getSession().getAttribute("domain"));
				//request.getSession().setMaxInactiveInterval(30 * 60);// 以秒为单位
			}
		} */
    filterChain.doFilter(servletRequest, servletResponse);
}


public void destroy(){
    notCheckURLList.clear();
}


}