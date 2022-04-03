package org.danyuan.application.common.config;
 import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.danyuan.application.softm.syslog.dao.SysComnLogsDao;
import org.danyuan.application.softm.syslog.po.SysComnLogs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import eu.bitwalker.useragentutils.Browser;
import eu.bitwalker.useragentutils.OperatingSystem;
import eu.bitwalker.useragentutils.UserAgent;
// 用于将切面声明为一个普通的类
@Aspect
// 将这个类引入spring容器中去
@Component
public class HttpAspect {

 public  String android;

 public  String iphone;

 public  String ipad;

 public  String noDevice;

@Autowired
 private SysComnLogsDao sysComnLogsDao;

 private  Logger logger;


@SuppressWarnings("unchecked")
// 在调用上面 @Pointcut标注的方法前执行以下方法
@Around("cut()")
public Object doBefore(ProceedingJoinPoint joinPoint) throws IllegalArgumentException{
    // 用于获取类方法
    SysComnLogs logs = new SysComnLogs();
    ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
    HttpServletRequest request = attributes.getRequest();
    logs.setUuid(UUID.randomUUID().toString());
    // url
    logs.setUrl(request.getRequestURI());
    // method
    logs.setMethod(request.getMethod());
    // ip
    logs.setIp(getIpAddr(request));
    // 类方法
    logs.setClassName(joinPoint.getSignature().getDeclaringTypeName());
    logs.setClassMethod(joinPoint.getSignature().getName());
    // 用户
    String user = ((User) ((SecurityContextImpl) request.getSession().getAttribute("SPRING_SECURITY_CONTEXT")).getAuthentication().getPrincipal()).getUsername();
    logs.setCreateUser(user);
    String paramList = "";
    // 参数
    ObjectMapper mapper = new ObjectMapper();
    String ss = "";
    try {
        ss = mapper.writeValueAsString(joinPoint.getArgs());
    } catch (JsonProcessingException Je) {
    }
    logs.setArgs(ss);
    String ua = request.getHeader("User-Agent");
    UserAgent userAgent = UserAgent.parseUserAgentString(ua);
    Browser browser = userAgent.getBrowser();
    logs.setBrowser(ua);
    logs.setBrowserType(browser.toString());
    OperatingSystem os = userAgent.getOperatingSystem();
    logs.setOs(os.toString());
    logs.setParamList(paramList);
    logs.setRequestLong(System.currentTimeMillis());
    logger.info("请求开始:" + logs.getClassName() + "." + logs.getClassMethod());
    Object result = null;
    try {
        result = joinPoint.proceed();
    } catch (Throwable e) {
        logger.error("exception:" + e.getMessage(), HttpAspect.class);
        logs.setMessage(e.getMessage().length() > 2000 ? e.getMessage().substring(0, 2000) : e.getMessage());
    // TODO
    }
    logs.setRequestLong(System.currentTimeMillis() - logs.getRequestLong());
    String[] mods = { "iPhone", "Windows Phone", "iPad", "Nokia" };
    for (String string : mods) {
        if (ua.contains(string)) {
            logs.setModel(string);
        }
    }
    if (logs.getModel() == null || "".equals(logs.getModel())) {
        String requestHeader = request.getHeader("User-Agent");
        int index_one = requestHeader.indexOf("(");
        String requestBody = requestHeader.substring(index_one + 1);
        String userInfo = requestBody.substring(0, requestBody.indexOf(")"));
        String[] userInfoList = userInfo.split(";");
        for (String string : userInfoList) {
            if (string.contains("Build")) {
                int index = string.indexOf("/");
                if (index > 0) {
                    string = string.substring(0, string.indexOf("/") - 5);
                    logs.setModel(string.trim());
                }
            }
        }
    }
    try {
        sysComnLogsDao.save(logs);
    } catch (Exception e) {
        logger.error(e.getMessage(), HttpAspect.class);
    }
    return result;
}


// 要处理的方法，包名+类名+方法名
@Pointcut("execution(public * org.danyuan.application.*.*.controller.*.*(..))")
public void cut(){
}


public String getIpAddr(HttpServletRequest request){
    String ip = request.getHeader("x-forwarded-for");
    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
        ip = request.getHeader("Proxy-Client-IP");
    }
    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
        ip = request.getHeader("WL-Proxy-Client-IP");
    }
    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
        ip = request.getRemoteAddr();
    }
    return ip.equals("0:0:0:0:0:0:0:1") ? "127.0.0.1" : ip;
}


}