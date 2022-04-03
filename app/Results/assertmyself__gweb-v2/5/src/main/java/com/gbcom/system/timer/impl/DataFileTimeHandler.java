package com.gbcom.system.timer.impl;
 import com.gbcom.system.manager.ConfigManager;
import com.gbcom.system.timer.ITimerHandler;
import com.gbcom.system.timer.service.SysTimerService;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import java.util.Properties;
public class DataFileTimeHandler implements ITimerHandler{

 private  Logger logger;

 private  String username;

 private  String password;

 private  String host;

 private  String port;

 private  String dbname;

@Autowired
 private  ConfigManager configManager;


public void setHost(String host){
    this.host = host;
}


public void setPassword(String password){
    this.password = password;
}


public String getPassword(){
    return password;
}


public void setDbname(String dbname){
    this.dbname = dbname;
}


public String getPort(){
    return port;
}


public void setUsername(String username){
    this.username = username;
}


@Override
public void handle(){
    // To change body of implemented methods use File | Settings | File
    // Templates.
    getConfig();
    // 得到配置文件
    try {
        Runtime rt = Runtime.getRuntime();
        // String backPath =
        // getServletContext().getRealPath("/")+"/backup/"+System.currentTimeMillis()+".sql";
        String backPath = configManager.getDbFilePath() + "/backup/" + System.currentTimeMillis() + ".sql";
        String mysql = "mysqldump -u" + getUsername() + " -p" + getPassword() + " --default-character-set=utf8 -h" + getHost() + " -P" + getPort() + " " + getDbname() + " >" + "\"" + backPath + "\"";
        // 这里实现暂时用mysqldump工具，仅支持本地数据库
        Properties prop = System.getProperties();
        String os = prop.getProperty("os.name");
        if (os.indexOf("windows") >= 0 || os.indexOf("Windows") >= 0) {
            // 设置导出编码为utf8。这里必须是utf8
            Process proc = rt.exec("cmd.exe /c " + mysql);
            // 等待进程终止
            int tag = proc.waitFor();
        } else if (os.indexOf("linux") >= 0 || os.indexOf("Linux") >= 0) {
            // 设置导出编码为utf8。这里必须是utf8
            Process proc = rt.exec(mysql);
            // 等待进程终止
            int tag = proc.waitFor();
        }
    // String backExe =
    // ServletActionContext.getServletContext().getRealPath("/")+"bin/mysqldump.exe";
    // String mysql = getDbname()+ " -u" + getUsername()+ " -p" +
    // getPassword() +
    // " --default-character-set=utf8 -h"+getHost()+" -P"+getPort()+" >"+"\""+backPath+"\"";
    } catch (Exception e) {
        e.printStackTrace();
        logger.error("mysqldump error", e);
    }
}


public String getDbname(){
    return dbname;
}


public void setPort(String port){
    this.port = port;
}


public String getHost(){
    return host;
}


public void getConfig(){
    // ApplicationContext context = new
    // ClassPathXmlApplicationContext("applicationContext.xml");
    // ApplicationContext context = new
    // ClassPathXmlApplicationContext("classpath:applicationContext.xml");
    // ApplicationContext context = new
    // FileSystemXmlApplicationContext("classpath:applicationContext.xml");
    // ServletContext servletContext = getServletContext();
    // String configFile =
    // servletContext.getRealPath("/")+"/WEB-INF/classes/resources/applicationContext.xml";
    // ApplicationContext context = new
    // FileSystemXmlApplicationContext("file:"+configFile);
    /*
		 * BasicDataSource ba = (BasicDataSource)context.getBean("dataSource");
		 * setUsername(ba.getUsername()); setPassword(ba.getPassword()); String
		 * url = ba.getUrl();
		 */
    ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
    // ApplicationContext context =
    // WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
    ComboPooledDataSource ba = (ComboPooledDataSource) context.getBean("dataSource");
    setUsername(ba.getUser());
    setPassword(ba.getPassword());
    String url = ba.getJdbcUrl();
    url = url.substring(13, url.length());
    String[] temp = url.split("/");
    String[] temp1 = temp[0].split(":");
    setHost(temp1[0]);
    setPort(temp1[1]);
    setDbname(temp[1]);
}


public String getUsername(){
    return username;
}


}