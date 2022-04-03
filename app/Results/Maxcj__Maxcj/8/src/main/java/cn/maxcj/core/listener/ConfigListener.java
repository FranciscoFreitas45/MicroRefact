package cn.maxcj.core.listener;
 import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.HashMap;
import java.util.Map;
public class ConfigListener implements ServletContextListener{

 private  Map<String,String> conf;


public Map<String,String> getConf(){
    return conf;
}


@Override
public void contextInitialized(ServletContextEvent evt){
    ServletContext sc = evt.getServletContext();
    // 项目发布,当前运行环境的绝对路径
    conf.put("realPath", sc.getRealPath("/").replaceFirst("/", ""));
    // servletContextPath,默认""
    conf.put("contextPath", sc.getContextPath());
}


@Override
public void contextDestroyed(ServletContextEvent arg0){
    conf.clear();
}


}