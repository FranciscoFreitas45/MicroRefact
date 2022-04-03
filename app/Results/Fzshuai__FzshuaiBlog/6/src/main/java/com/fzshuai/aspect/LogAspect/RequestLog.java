package com.fzshuai.aspect.LogAspect;
 import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
public class RequestLog {

 private  String url;

 private  String ip;

 private  String classMethod;

 private  Object[] args;

public RequestLog(String url, String ip, String classMethod, Object[] args) {
    this.url = url;
    this.ip = ip;
    this.classMethod = classMethod;
    this.args = args;
}
@Override
public String toString(){
    return "{" + "url='" + url + '\'' + ", ip='" + ip + '\'' + ", classMethod='" + classMethod + '\'' + ", args=" + Arrays.toString(args) + '}';
}


}