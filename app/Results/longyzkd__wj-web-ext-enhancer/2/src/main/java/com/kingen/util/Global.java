package com.kingen.util;
 import java.io.File;
import java.io.IOException;
import java.util.Map;
import org.springframework.core.io.DefaultResourceLoader;
import com.google.common.collect.Maps;
public class Global {

 private  Global global;

 private  Map<String,String> map;

 private  PropertiesLoader loader;

 public  String SHOW;

 public  String HIDE;

 public  String YES;

 public  String NO;

 public  String TRUE;

 public  String FALSE;

 public  String USERFILES_BASE_URL;


public String getFrontPath(){
    return getConfig("frontPath");
}


public Boolean isDemoMode(){
    String dm = getConfig("demoMode");
    return "true".equals(dm) || "1".equals(dm);
}


public String getUrlSuffix(){
    return getConfig("urlSuffix");
}


public String getAdminPath(){
    return getConfig("adminPath");
}


public String getProjectPath(){
    // 如果配置了工程路径，则直接返回，否则自动获取。
    String projectPath = Global.getConfig("projectPath");
    if (StringUtils.isNotBlank(projectPath)) {
        return projectPath;
    }
    try {
        File file = new DefaultResourceLoader().getResource("").getFile();
        if (file != null) {
            while (true) {
                File f = new File(file.getPath() + File.separator + "src" + File.separator + "main");
                if (f == null || f.exists()) {
                    break;
                }
                if (file.getParentFile() != null) {
                    file = file.getParentFile();
                } else {
                    break;
                }
            }
            projectPath = file.toString();
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
    return projectPath;
}


public Boolean isSynActivitiIndetity(){
    String dm = getConfig("activiti.isSynActivitiIndetity");
    return "true".equals(dm) || "1".equals(dm);
}


public Object getConst(String field){
    try {
        return Global.class.getField(field).get(null);
    } catch (Exception e) {
    // 异常代表无配置，这里什么也不做
    }
    return null;
}


public Global getInstance(){
    return global;
}


public String getConfig(String key){
    String value = map.get(key);
    if (value == null) {
        value = loader.getProperty(key);
        map.put(key, value != null ? value : StringUtils.EMPTY);
    }
    return value;
}


public String getUserfilesBaseDir(){
    String dir = getConfig("userfiles.basedir");
    if (StringUtils.isBlank(dir)) {
        try {
        // dir = ServletContextFactory.getServletContext().getRealPath("/");
        } catch (Exception e) {
            return "";
        }
    }
    if (!dir.endsWith("/")) {
        dir += "/";
    }
    // System.out.println("userfiles.basedir: " + dir);
    return dir;
}


}