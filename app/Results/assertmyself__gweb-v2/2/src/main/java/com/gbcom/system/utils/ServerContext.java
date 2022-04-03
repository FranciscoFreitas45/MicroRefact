package com.gbcom.system.utils;
 import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
public class ServerContext {

 private  ThreadLocal<HttpServletRequest> requestLocal;

 private  ThreadLocal<HttpServletResponse> responseLocal;


public void setRequest(HttpServletRequest request){
    requestLocal.set(request);
}


public HttpServletRequest getRequest(){
    return (HttpServletRequest) requestLocal.get();
}


public HttpServletResponse getResponse(){
    return (HttpServletResponse) responseLocal.get();
}


public void setResponse(HttpServletResponse response){
    responseLocal.set(response);
}


public HttpSession getSession(){
    // 拦截器异常
    HttpServletRequest request = (HttpServletRequest) requestLocal.get();
    if (request != null) {
        HttpSession session = null;
        try {
            session = (HttpSession) request.getSession();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return session;
    } else {
        return null;
    }
}


}