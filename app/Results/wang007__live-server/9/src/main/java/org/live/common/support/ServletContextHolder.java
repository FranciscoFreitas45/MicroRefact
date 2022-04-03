package org.live.common.support;
 import org.live.common.constants.SystemConfigConstants;
import org.live.config.dataComponent.SystemConfigDataComponent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;
import javax.annotation.Resource;
import javax.servlet.ServletContext;
import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;
@Component
public class ServletContextHolder implements ServletContextAware{

 private  Logger LOGGER;

@Resource
 private  SystemConfigDataComponent systemConfig;

@Resource
 private  UploadFilePathConfig pathConfig;

@Resource
 private  Environment env;

 private  ServletContext servletContext;


public void setAttribute(String name,Object value){
    servletContext.setAttribute(name, value);
}


public void initProperties(ServletContext servletContext){
    LOGGER.info("执行常量的serlvetContext属性的初始化操作");
    String currentOS = System.getProperty("os.name").toLowerCase();
    LOGGER.info("current os ---> {}", currentOS);
    // 文件上传的根路径
    String uploadFileRootPath = null;
    if (currentOS.contains("windows")) {
        uploadFileRootPath = systemConfig.getWindowsUploadFileRootPath();
    } else {
        uploadFileRootPath = systemConfig.getLinuxUploadFileRootPath();
    }
    // 文件上传的路径前缀
    String uploadFilePrefix = "/" + systemConfig.getUploadFilePathPrefix();
    // 文件上传路径
    String uploadFilePath = uploadFileRootPath + File.separator + systemConfig.getUploadFilePathPrefix();
    pathConfig.setUploadFileRootPath(uploadFileRootPath);
    pathConfig.setUploadFilePathPrefix(uploadFilePrefix);
    pathConfig.setUploadFilePath(uploadFilePath);
    // 系统标题
    servletContext.setAttribute(SystemConfigConstants.SYSTEM_TITLE_KEY, systemConfig.getTitle());
    try {
        InetAddress localHost = InetAddress.getLocalHost();
        servletContext.setAttribute(SystemConfigConstants.SYSTEM_IP_KEY, localHost.getHostAddress());
        LOGGER.info("本地ip地址---> " + localHost.getHostAddress());
    } catch (UnknownHostException e) {
        throw new RuntimeException("获取本地的ip地址异常", e);
    }
    String rtmpAddrPrefix = env.getProperty("system.rtmpAddrPrefix");
    servletContext.setAttribute(SystemConfigConstants.RTMP_ADDR_PREFIX_KEY, rtmpAddrPrefix);
    LOGGER.info("rtmp地址前缀——>" + rtmpAddrPrefix);
    servletContext.setAttribute(UploadFilePathConfig.UPLOAD_FILE_ROOT_PATH_KEY, uploadFileRootPath);
    servletContext.setAttribute(UploadFilePathConfig.UPLOAD_FILE_PATH_PREFIX_KEY, uploadFilePrefix);
    servletContext.setAttribute(UploadFilePathConfig.UPLOAD_FILE_PATH_KEY, uploadFilePath);
    LOGGER.info("system title ---> {}", systemConfig.getTitle());
    LOGGER.info("uplaodFileRootPath ---> {}", uploadFileRootPath);
    LOGGER.info("uploadFilePathPrefix ---> {}", uploadFilePrefix);
    LOGGER.info("uploadFilePath ---> {}", uploadFilePath);
}


public T getAttribute(String name){
    return (T) servletContext.getAttribute(name);
}


public ServletContext getServletContext(){
    return servletContext;
}


@Override
public void setServletContext(ServletContext servletContext){
    initProperties(servletContext);
    ServletContextHolder.servletContext = servletContext;
}


}