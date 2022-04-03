package org.jeecgframework.web.system.listener;
 import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import org.jeecgframework.core.util.LogUtil;
import org.jeecgframework.web.system.manager.ClientManager;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
public class OnlineListener implements HttpSessionListener,ServletContextListener{

 private  ApplicationContext ctx;

public OnlineListener() {
}
public void sessionCreated(HttpSessionEvent httpSessionEvent){
}


public void sessionDestroyed(HttpSessionEvent httpSessionEvent){
    try {
        ClientManager.getInstance().removeClinet(httpSessionEvent.getSession().getId());
    } catch (Exception e) {
        e.printStackTrace();
    }
}


public void contextInitialized(ServletContextEvent evt){
    ctx = WebApplicationContextUtils.getWebApplicationContext(evt.getServletContext());
}


public ApplicationContext getCtx(){
    return ctx;
}


public void contextDestroyed(ServletContextEvent paramServletContextEvent){
}


}