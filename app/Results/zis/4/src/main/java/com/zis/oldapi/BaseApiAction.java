package com.zis.oldapi;
 import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import com.alibaba.fastjson.JSON;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.zis.api.response.BaseApiResponse;
public class BaseApiAction extends ActionSupport{

 private  long serialVersionUID;

 private  Logger logger;

 public  String ZIS_TOKEN;

 protected  String token;


public void setSessionToken(String sessionToken){
    ActionContext ctx = ActionContext.getContext();
    ctx.getSession().put(ZIS_TOKEN, sessionToken);
    String sessionId = ServletActionContext.getRequest().getSession().getId();
    logger.info("sessionId = " + sessionId);
}


public void clearSessionToken(){
    Map<String, Object> session = ActionContext.getContext().getSession();
    if (session.containsKey(ZIS_TOKEN)) {
        session.remove(ZIS_TOKEN);
    }
}


public void setToken(String token){
    this.token = token;
}


public String getToken(){
    return token;
}


public void renderErrResult(String errMsg){
    BaseApiResponse response = new BaseApiResponse();
    response.setCode(BaseApiResponse.CODE_INNER_ERROR);
    response.setMsg("系统内部错误：" + errMsg);
    renderResult(response);
}


public String checkToken(boolean forceTokenCheck){
    String sessionId = ServletActionContext.getRequest().getSession().getId();
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
    ActionContext ctx = ActionContext.getContext();
    String sessionToken = (String) ctx.getSession().get(ZIS_TOKEN);
    if (StringUtils.isBlank(sessionToken)) {
        return "操作成功，请勿重复提交";
    }
    // session中token和request中的不一致
    if (!token.equals(sessionToken)) {
        return "无效token";
    }
    return StringUtils.EMPTY;
}


public void renderResult(Object obj){
    // json序列化
    String content = JSON.toJSONString(obj);
    // 输出结果
    ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
    try {
        PrintWriter out = ServletActionContext.getResponse().getWriter();
        out.print(content);
        out.flush();
        out.close();
    } catch (IOException e) {
        // 异常处理，异常情况及堆栈信息打印到日志中
        logger.error("渲染API结果出现异常", e);
    }
}


}