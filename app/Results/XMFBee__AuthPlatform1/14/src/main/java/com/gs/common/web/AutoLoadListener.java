package com.gs.common.web;
 import com.gs.common.util.SingletonConfig;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
public class AutoLoadListener implements ServletContextListener{


@Override
public void contextInitialized(ServletContextEvent servletContextEvent){
    System.out.println("AutoLoadListener init!");
    String autoloadConfig = servletContextEvent.getServletContext().getInitParameter("autoloadConfigLocation");
    SingletonConfig config = SingletonConfig.getInstance();
    config.build(autoloadConfig);
}


@Override
public void contextDestroyed(ServletContextEvent servletContextEvent){
    System.out.println("AutoLoadListener destroyed!");
}


}