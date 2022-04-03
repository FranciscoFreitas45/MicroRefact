package com.zis.api;
 import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.apache.shiro.web.servlet.ShiroHttpSession;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import com.alibaba.fastjson.JSON;
import com.zis.api.response.BaseApiResponse;
public class BaseApiController {

 private  Logger logger;

 public  String ZIS_TOKEN;


public void setSessionToken(String sessionToken){
    ShiroHttpServletRequest request = getRequest();
    request.getSession().setAttribute(ZIS_TOKEN, sessionToken);
    String sessionId = request.getSession().getId();
    ;
    logger.info("sessionId = " + sessionId);
}


public void clearSessionToken(){
    ShiroHttpServletRequest request = (ShiroHttpServletRequest) getRequest();
    ShiroHttpSession session = (ShiroHttpSession) request.getSession();
    Object token = session.getAttribute(ZIS_TOKEN);
    if (token != null) {
        session.removeAttribute(ZIS_TOKEN);
    }
// Enumeration<Object> keys = session.getAttributeNames();
// while(keys.hasMoreElements()){
// Object o = keys.nextElement();
// if(o.equals(ZIS_TOKEN)){
// session.removeAttribute(ZIS_TOKEN);
// }
// }
}


public ShiroHttpServletRequest getRequest(){
    ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
    HttpServletRequest request = attributes.getRequest();
    return (ShiroHttpServletRequest) request;
}


public void renderErrResult(String errMsg,HttpServletResponse response){
    BaseApiResponse resp = new BaseApiResponse();
    resp.setCode(BaseApiResponse.CODE_INNER_ERROR);
    resp.setMsg("系统内部错误：" + errMsg);
    renderResult(resp, response);
}


public String checkToken(boolean forceTokenCheck,String token){
    String sessionId = getRequest().getSession().getId();
    logger.info("sessionId = " + sessionId);
    // request中不存在token
    if (StringUtils.isBlank(token)) {
        // 如果强制token检查，则返回false；否则直接返回true（兼容没有token的版本）
        if (forceTokenCheck) {
            return "无效请求，token不能为空";
        } else {
            return StringUtils.EMPTY;
        }
    }
    // session中不存在token
    ShiroHttpServletRequest request = getRequest();
    String sessionToken = (String) request.getSession().getAttribute(ZIS_TOKEN);
    if (StringUtils.isBlank(sessionToken)) {
        return "操作成功，请勿重复提交";
    }
    // session中token和request中的不一致
    if (!token.equals(sessionToken)) {
        return "无效token";
    }
    return StringUtils.EMPTY;
}


public void renderResult(Object obj,HttpServletResponse response){
    // json序列化
    String content = JSON.toJSONString(obj);
    // 输出结果
    // HttpServletResponse resp = ((ServletWebRequest)RequestContextHolder.getRequestAttributes()).getResponse();
    response.setContentType("text/html;charset=utf-8");
    // ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
    try {
        PrintWriter out = response.getWriter();
        out.print(content);
        out.flush();
        out.close();
    } catch (IOException e) {
        // 异常处理，异常情况及堆栈信息打印到日志中
        logger.error("渲染API结果出现异常", e);
    }
}


}