package edu.xr.campusweibo.web.filter;
 import edu.xr.campusweibo.config.Constants;
import edu.xr.campusweibo.domain.MyUser;
import edu.xr.campusweibo.web.rest.util.JsonUtil;
import edu.xr.campusweibo.web.rest.util.ResponseResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import javax.servlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
public class LoginFilter implements Filter{

 private  Logger logger;

 private  String loginUrl;

 private  String registerUrl;


@Override
public void init(FilterConfig filterConfig){
    this.loginUrl = filterConfig.getInitParameter("LOGIN_URL");
    this.registerUrl = filterConfig.getInitParameter("REGISTER_URL");
}


@Override
public void doFilter(ServletRequest servletRequest,ServletResponse servletResponse,FilterChain filterChain){
    logger.info("start to doFilter access url!");
    // 设置编码utf-8
    HttpServletResponse response = (HttpServletResponse) servletResponse;
    response.setHeader("Access-Control-Allow-Origin", "*");
    response.setHeader("Access-Control-Allow-Methods", "POST");
    response.setHeader("Access-Control-Allow-Headers", "x-requested-with,content-type,userId");
    servletRequest.setCharacterEncoding("UTF-8");
    servletResponse.setCharacterEncoding("UTF-8");
    // 配合登录需要创建session，保存用户的登录状态
    HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
    HttpSession session = httpRequest.getSession(true);
    // XSystem.out.println("session的默认超时时间是：" + session.getMaxInactiveInterval() + "");
    // 获取访问的url
    String accessUrl = httpRequest.getRequestURI();
    logger.info("access url is {}", accessUrl);
    // 如果是访问登录的url，就继续访问
    if (this.loginUrl.equals(accessUrl)) {
        logger.info("=======这是登陆url，不过滤，继续登陆");
        filterChain.doFilter(servletRequest, servletResponse);
        return;
    }
    // 如果是注册url，就继续注册
    if (this.registerUrl.equals(accessUrl)) {
        logger.info("=======这是注册URL，不过滤，继续注册.");
        filterChain.doFilter(servletRequest, servletResponse);
        return;
    }
    // 如果都不是判断是否已经登录
    String userIdStr = httpRequest.getHeader(Constants.REQUEST_HEADER_INFO_KEY);
    if (!StringUtils.hasText(userIdStr)) {
        logger.info("not find userId in header params");
        servletResponse.setContentType("application/json; charset=utf-8");
        servletResponse.getWriter().write(JsonUtil.toJson(new ResponseResult(Constants.FAIL_CODE, Constants.SESSION_TIMEOUT_OR_NO_AUTHORITY)));
        return;
    }
    // if (session.getAttribute(Constants.SEESION_STORE_USERINFO_KEY) == null) {
    // logger.info("not find userInfo in session,so return back!");
    // servletResponse.setContentType("application/json; charset=utf-8");
    // servletResponse.getWriter().write(JsonUtil.toJson(new ResponseResult(Constants.FAIL_CODE, Constants.SESSION_TIMEOUT_OR_NO_AUTHORITY)));
    // return;
    // }
    // MyUser user = null;
    // try {
    // user = (MyUser) session.getAttribute(Constants.SEESION_STORE_USERINFO_KEY);
    // } catch (ClassCastException e) {
    // logger.error("get session store object cast to MsUser appear ClassCastException!", e);
    // }
    Long userId = null;
    try {
        userId = Long.parseLong(userIdStr);
    } catch (NumberFormatException e) {
        logger.error("parse userId  = " + userIdStr + " to long type appear number format exception!", e);
    }
    // if (user != null && user.getId() == userId) {
    filterChain.doFilter(servletRequest, servletResponse);
    return;
// }
// logger.info("this access url is not authoriezd，so return back.");
// servletResponse.setContentType("application/json; charset=utf-8");
// servletResponse.getWriter().write(JsonUtil.toJson(new ResponseResult(Constants.FAIL_CODE, Constants.SESSION_TIMEOUT_OR_NO_AUTHORITY)));
// return;
}


@Override
public void destroy(){
}


}