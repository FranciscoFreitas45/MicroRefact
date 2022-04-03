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
public class SqlExportManager {

 private  String DEFAULT_MYSQL_DIR;

 private  Logger LOG;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://11";


public String getExportCommand(Properties properties,String path){
    final StringBuffer command = new StringBuffer();
    // 用户名
    final String username = properties.getProperty("user");
    // 用户密码
    final String password = properties.getProperty("password");
    // 需要导出的数据库名
    final String exportDBName = properties.getProperty("databasename");
    // boolean isAll =
    // Boolean.valueOf(properties.getProperty("jdbc.isAll"));
    // isAll = true;
    // String host = properties.getProperty("db.host");//
    // 从哪个主机导出数据库，如果没有指定这个值，则默认取localhost
    // String port = properties.getProperty("db.port");// 使用的端口号
    // String characterset = properties.getProperty("db.characterset");
    // 注意哪些地方要空格，哪些不要空格
    // String tables = "";
    // if (!isAll) {
    // tables = " bssidmap cpedevice location plan upgpolicy";
    // }
    // /usr/local/mysql/bin/mysqldump
    // command.append("mysqldump -u").append(username).append(" -p").append(password).append(" ")//
    // 密码是用的小p，而端口是用的大P。
    // .append(exportDBName).append(tables).append(" -r ").append("\"" +
    // path + "\"");
    command.append("mysqldump  -u").append(username).append(" -p").append(password).append(// 密码是用的小p，而端口是用的大P。
    " ").append(exportDBName).append(" -r ").append("\"" + path + "\"");
    return command.toString();
}


public boolean exportSql(String exportPath){
    // boolean isSuccess = exportFile(exportPath);
    // if (isSuccess) {
    // String ftpFilePath = CmConst.fileDir + "//" + fileName;
    // putFileToFtp(exportPath, ftpFilePath);
    // return ftpFilePath;
    // }
    return exportFile(exportPath);
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/exportSql"))

.queryParam("exportPath",exportPath)
;
boolean aux = restTemplate.getForObject(builder.toUriString(),boolean.class);
return aux;
}


}