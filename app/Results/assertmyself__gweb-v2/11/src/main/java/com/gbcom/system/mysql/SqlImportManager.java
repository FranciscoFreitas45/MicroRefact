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
public class SqlImportManager {

 private  String DEFAULT_MYSQL_DIR;

 private  Logger LOG;


public boolean importSqlForLinux(Properties properties,String importPath){
    String command = "mysql -u " + properties.getProperty("user") + " -p" + properties.getProperty("password") + " ccsv3 < " + importPath;
    LOG.info("command======" + command);
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


public boolean importSqlForWindows(Properties properties,String importPath){
    String mysqlPath = DEFAULT_MYSQL_DIR;
    // mysqlPath = DbPathSqlManager.getMySqlPath();
    mysqlPath = ((MysqlManager) SpringUtils.getBean("MysqlManager")).getRealMySqlPath() + "bin";
    LOG.info("Mysql-DIR :" + mysqlPath);
    // 因为在命令窗口进行mysql数据库的导入一般分三步走，所以所执行的命令将以字符串数组的形式出现
    // 根据属性文件的配置获取数据库导入所需的命令，组成一个数组
    final String[] cmdarray = getImportCommand(properties, importPath);
    // runtime.exec(cmdarray);//这里也是简单的直接抛出异常
    CmdInfo info;
    // String mysqlPath = userDir.substring(0,
    // userDir.length()-3)+"MYSQL5.0\\bin";
    final String root = mysqlPath.split("\\\\")[0];
    LOG.info("mysqlPaht is : " + mysqlPath);
    final String mainCmd = "C:/WINDOWS/system32/cmd.exe /c " + root + " & cd \\ & cd " + mysqlPath + " & " + cmdarray[0];
    final String mainCmdInstead = "C:/WINDOWS/system32/cmd.exe /c " + root + " & cd \\ & cd " + mysqlPath + " & " + cmdarray[0] + " " + properties.getProperty("databasename") + "< " + importPath;
    final String cmdInstead = properties.getProperty("databasename") + "< " + importPath;
    info = RunTimeUtil.exec(mainCmdInstead);
    // info = RunTimeUtil.exec(mainCmd, cmdarray[1] + "\r\n" + cmdarray[2]);
    final int result = info.getExitValue();
    return result == 0;
}


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
}


}