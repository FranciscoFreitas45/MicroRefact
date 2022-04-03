package com.gbcom.system.mysql;
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


public boolean exportFile(String exportPath){
    // 根据操作系统分别处理
    if (System.getProperties().getProperty("os.name").equals("Linux")) {
        return exportFileForLinux(exportPath);
    } else {
        return exportFileForWindows(exportPath);
    }
}


public boolean exportSql(String exportPath){
    // boolean isSuccess = exportFile(exportPath);
    // if (isSuccess) {
    // String ftpFilePath = CmConst.fileDir + "//" + fileName;
    // putFileToFtp(exportPath, ftpFilePath);
    // return ftpFilePath;
    // }
    return exportFile(exportPath);
}


public boolean exportFileForWindows(String exportPath){
    String mysqlPath = DEFAULT_MYSQL_DIR;
    // mysqlPath = DbPathSqlManager.getMySqlPath();
    final String profilePath = Thread.currentThread().getContextClassLoader().getResource("/context/application.properties").getPath();
    mysqlPath = ((MysqlManager) SpringUtils.getBean("MysqlManager")).getRealMySqlPath() + "bin";
    LOG.info("PROPERTIES-DIR :" + profilePath);
    LOG.info("Mysql-DIR :" + mysqlPath);
    LOG.info("-- LOAD properties file!");
    final InputStream input = new FileInputStream(profilePath);
    // final InputStream input =
    // Thread.currentThread().getContextClassLoader().getResourceAsStream(
    // "jdbc.properties");
    final Properties properties = new Properties();
    properties.load(input);
    final String root = mysqlPath.split("\\\\")[0];
    final String command = getExportCommand(properties, exportPath);
    LOG.info("mysqlPaht is : " + mysqlPath + " cmd " + "C:/WINDOWS/system32/cmd.exe /c " + root + " & cd \\ & cd " + mysqlPath + " & " + command);
    final CmdInfo info = RunTimeUtil.exec("C:/WINDOWS/system32/cmd.exe /c " + root + " & cd \\ & cd " + mysqlPath + " & " + command);
    final int exitVal = info.getExitValue();
    LOG.info(info.getErrorStreamInfo());
    LOG.info(info.getOutputStreamInfo());
    LOG.info("exitVal: " + exitVal);
    // 如果是0,则表示正常中止
    return exitVal == 0 ? true : false;
}


public boolean exportFileForLinux(String exportPath){
    final String profilePath = Thread.currentThread().getContextClassLoader().getResource("/context/application.properties").getPath();
    LOG.info("PROPERTIES-DIR :" + profilePath);
    LOG.info("-- LOAD properties file!");
    final InputStream input = new FileInputStream(profilePath);
    final Properties properties = new Properties();
    properties.load(input);
    final String command = getExportCommand(properties, exportPath);
    LOG.info("mysql_command:" + command);
    Process pro = Runtime.getRuntime().exec(new String[] { "/bin/sh", "-c", command });
    int exitVal = 1;
    try {
        exitVal = pro.waitFor();
    } catch (InterruptedException e) {
        LOG.error(e.getMessage(), e);
    }
    // 这个输入流是获取shell输出的
    BufferedReader reader = new BufferedReader(new InputStreamReader(pro.getInputStream()));
    String line;
    while ((line = reader.readLine()) != null) {
        LOG.info("shell_out:" + line);
    }
    reader.close();
    pro.destroy();
    // 如果是0,则表示正常中止
    return exitVal == 0 ? true : false;
}


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


}