package com.gbcom.DTO;
 import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;
import org.apache.log4j.Logger;
import com.gbcom.op.util.RunTimeUtil;
import com.gbcom.op.util.RunTimeUtil.CmdInfo;
import com.gbcom.system.manager.MysqlManager;
import com.hc.core.utils.SpringUtils;
public class SqlImportManager {

 private  String DEFAULT_MYSQL_DIR;

 private  Logger LOG;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://11";


public String[] getImportCommand(Properties properties,String importPath){
    // 用户名
    final String username = properties.getProperty("user");
    // 密码
    final String password = properties.getProperty("password");
    // String host = properties.getProperty("db.host");// 导入的目标数据库所在的主机
    // String port = properties.getProperty("db.port");// 使用的端口号
    // 导入的目标数据库的名称
    final String importDBName = properties.getProperty("databasename");
    // 第一步，获取登录命令语句
    final String loginCommand = new StringBuffer().append("mysql -u").append(username).append(" -p").append(password).toString();
    // 第二步，获取切换数据库到目标数据库的命令语句
    final String switchCommand = new StringBuffer("use ").append(importDBName).toString();
    // 第三步，获取导入的命令语句
    final String importCommand = new StringBuffer("source ").append(importPath).toString();
    // 需要返回的命令语句数组
    final String[] commands = new String[] { loginCommand, switchCommand, importCommand };
    return commands;
}


public boolean importSql(String filePath){
    // String filePath = getFile(ftpPath);
    final String profilePath = Thread.currentThread().getContextClassLoader().getResource("/context/application.properties").getPath();
    LOG.info("PROPERTIES-DIR :" + profilePath);
    final InputStream input = new FileInputStream(profilePath);
    // InputStream is =
    // Thread.currentThread().getContextClassLoader().getResourceAsStream(
    // "jdbc.properties");
    final Properties properties = new Properties();
    properties.load(input);
    // 根据操作系统分别处理
    if (System.getProperties().getProperty("os.name").equals("Linux")) {
        return importSqlForLinux(properties, filePath);
    } else {
        return importSqlForWindows(properties, filePath);
    }
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/importSql"))

.queryParam("filePath",filePath)
;
boolean aux = restTemplate.getForObject(builder.toUriString(),boolean.class);
return aux;
}


}