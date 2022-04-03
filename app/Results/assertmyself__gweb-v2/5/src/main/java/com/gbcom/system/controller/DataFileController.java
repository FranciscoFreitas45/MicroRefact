package com.gbcom.system.controller;
 import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Date;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.support.WebApplicationContextUtils;
import com.gbcom.common.hibernate.GridJq;
import com.gbcom.system.aop.UserLog;
import com.gbcom.system.domain.DataFile;
import com.gbcom.system.manager.ConfigManager;
import com.hc.core.controller.BaseCRUDActionController;
import com.hc.core.orm.hibernate.Page;
import com.jspsmart.upload.SmartUpload;
import com.mchange.v2.c3p0.ComboPooledDataSource;
@Controller
public class DataFileController extends BaseCRUDActionController<DataFile>{

 private  Logger logger;

 private  String username;

 private  String password;

 private  String host;

 private  String port;

 private  String dbname;

 private  List dataFiles;

 private  File reductionFile;

@Autowired
 private  ConfigManager configManager;


public void setHost(String host){
    this.host = host;
}


public void setPassword(String password){
    this.password = password;
}


public void setDbname(String dbname){
    this.dbname = dbname;
}


@RequestMapping
@UserLog(eventType = UserLog.USERLOG_EVENTTYPE_NEOPER)
public void backup(HttpServletResponse response,HttpServletRequest request){
    getConfig();
    // 得到配置文件
    try {
        Runtime rt = Runtime.getRuntime();
        // String backPath = getServletContext().getRealPath("/")+"/backup/"+System.currentTimeMillis()+".sql";
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
        // String backExe = ServletActionContext.getServletContext().getRealPath("/")+"bin/mysqldump.exe";
        // String mysql = getDbname()+ " -u" + getUsername()+ " -p" + getPassword() + " --default-character-set=utf8 -h"+getHost()+" -P"+getPort()+" >"+"\""+backPath+"\"";
        sendSuccessJSON(response, "备份成功");
    } catch (Exception e) {
        super.processException(response, e);
    }
}


@RequestMapping
@UserLog(eventType = UserLog.USERLOG_EVENTTYPE_NEOPER)
public void recover(HttpServletResponse response,String name){
    String sqlPath = "";
    // 支持导入文件进行还原，可暂不支持
    if (reductionFile != null) {
        String name2 = upload(reductionFile);
        sqlPath = configManager.getFilePath() + "/backup/" + name2;
    } else {
        sqlPath = configManager.getFilePath() + "/backup/" + name;
    }
    // System.out.println(sqlPath);
    if (sqlPath.substring(sqlPath.lastIndexOf(".") + 1).equals("sql")) {
        // 得到配置文件
        getConfig();
        try {
            Runtime rt = Runtime.getRuntime();
            String createDb = "mysqladmin -u" + getUsername() + " -p" + getPassword() + " create " + getDbname();
            // +"\""+backPath+"\""
            String mysql = "mysql -u" + getUsername() + " -p" + getPassword() + " " + getDbname() + " <" + "\"" + sqlPath + "\"";
            // 这里实现暂时用mysql工具，仅支持本地数据库
            Properties prop = System.getProperties();
            String os = prop.getProperty("os.name");
            if (os.indexOf("windows") >= 0 || os.indexOf("Windows") >= 0) {
                rt.exec("cmd.exe /c " + createDb);
                Process proc = rt.exec("cmd.exe /c " + mysql);
                // 等待进程终止
                int tag = proc.waitFor();
            } else if (os.indexOf("linux") >= 0 || os.indexOf("Linux") >= 0) {
                rt.exec(createDb);
                Process proc = rt.exec(mysql);
                // 等待进程终止
                int tag = proc.waitFor();
            }
            sendSuccessJSON(response, "还原成功");
        } catch (Exception e) {
            super.processException(response, e);
        }
    }
}


public void setUsername(String username){
    this.username = username;
}


public File getReductionFile(){
    return reductionFile;
}


public String upload(File file){
    String name = "";
    try {
        DataInputStream in = new DataInputStream(new FileInputStream(file));
        // FileInputStream in = new FileInputStream(file);
        String backPath = configManager.getDbFilePath() + "/backup/";
        name = System.currentTimeMillis() + ".sql";
        backPath = backPath + name;
        // FileOutputStream out = new FileOutputStream(new File(backPath));
        DataOutputStream out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(backPath)));
        int b = -1;
        while ((b = in.read()) != -1) {
            out.write(b);
        }
        out.close();
        in.close();
    } catch (Exception e) {
        // TODO Auto-generated catch block
        logger.error("Exception:", e);
    }
    return name;
}


public List getDataFiles(){
    return dataFiles;
}


public String getDbname(){
    return dbname;
}


public void setPort(String port){
    this.port = port;
}


@RequestMapping
@UserLog
public void delete(HttpServletResponse response,String name){
    String fileName = name;
    System.out.println(fileName);
    String backPath = configManager.getDbFilePath() + "/backup/" + fileName;
    File file = new File(backPath);
    file.delete();
    sendSuccessJSON(response, "删除成功");
}


public void getConfig(){
    // ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
    // ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
    // ApplicationContext context = new FileSystemXmlApplicationContext("classpath:applicationContext.xml");
    // ServletContext servletContext = getServletContext();
    // String configFile = servletContext.getRealPath("/")+"/WEB-INF/classes/resources/applicationContext.xml";
    // ApplicationContext context = new FileSystemXmlApplicationContext("file:"+configFile);
    /*BasicDataSource ba = (BasicDataSource)context.getBean("dataSource");
        setUsername(ba.getUsername());
        setPassword(ba.getPassword());
        String url = ba.getUrl();    */
    ApplicationContext context = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
    ComboPooledDataSource ba = (ComboPooledDataSource) context.getBean("dataSource");
    setUsername(ba.getUser());
    setPassword(ba.getPassword());
    String url = ba.getJdbcUrl();
    url = url.substring(13, url.length());
    String[] temp = url.split("/");
    String[] temp1 = temp[0].split(":");
    setHost(temp1[0]);
    setPort(temp1[1]);
    /*
        for (int i = 0; i < temp[1].length(); i++) {
            String temp2 = temp[1].charAt(i)+"";
            if(temp2.equals("?")){
                setDbname(temp[1].substring(0,5));
            }
        }*/
    setDbname(temp[1]);
}


public String getUsername(){
    return username;
}


public String getPassword(){
    return password;
}


@RequestMapping
@UserLog(eventType = UserLog.USERLOG_EVENTTYPE_NEOPER)
public void download(HttpServletResponse response,String name,HttpServletRequest request){
    String fileName = name;
    String backPath = configManager.getDbFilePath() + "/backup/" + fileName;
    // String backPath = "/backup/"+fileName;
    try {
        // 新建一个SmartUpload对象
        SmartUpload su = new SmartUpload();
        // 初始化
        ServletContext context = getServletContext();
        // ServletConfig config = (ServletConfig) context.getAttribute("servletConfig");
        su.initialize(context, null, request, response, null);
        // 设定contentDisposition为null以禁止浏览器自动打开文件，
        // 保证点击链接后是下载文件。若不设定，则下载的文件扩展名为doc时，浏览器将自动用word打开它。
        // 扩展名为pdf时，浏览器将用acrobat打开。
        su.setContentDisposition(null);
        // 下载文件
        su.downloadFile(backPath);
    } catch (Exception e) {
        logger.error("Exception:", e);
    }
// sendSuccessJSON(response, "下载成功");
}


public String getPort(){
    return port;
}


@RequestMapping
@UserLog
public String grid(Model model){
    // 判断是否有编辑权限
    // model.addAttribute("canEdit", true);
    System.out.println("view/system/dataFile/grid");
    return "view/system/dataFile/grid";
}


public void setDataFiles(List dataFiles){
    this.dataFiles = dataFiles;
}


@RequestMapping
public void gridDataCustom(HttpServletResponse response,String filters,String columns,int page,int rows){
    String json = "";
    Page<DataFile> pageModel = new Page<DataFile>(page, rows, true);
    dataFiles.clear();
    String backPath = "";
    // ServletContext context = request.getSession(true).getServletContext();
    backPath = configManager.getDbFilePath() + "/backup/";
    System.out.println("backPath=" + backPath);
    File file = new File(backPath);
    try {
        if (!file.exists()) {
            pageModel.setTotal(0);
            pageModel.setRecords(0);
            pageModel.setPage(page);
            pageModel.setRows(dataFiles);
        } else {
            File[] file1 = file.listFiles();
            int records = 0;
            pageModel.setTotal(file1.length);
            // page从1开始
            for (int i = (0 + (page - 1) * rows); i < file1.length; i++) {
                // if(file1[i].getName().equals("xx.txt")) continue;
                SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
                // 前面的lSysTime是秒数，先乘1000得到毫秒数，再转为java.util.Date类型
                java.util.Date dt = new Date(file1[i].lastModified());
                // 得到精确到秒的表示：08/31/2006 21:08:00
                String sDateTime = sdf.format(dt);
                DataFile dataFile = new DataFile();
                dataFile.setId(Long.valueOf(i + 1));
                dataFile.setFileName(file1[i].getName());
                dataFile.setFileDate(sDateTime);
                String filePath = "/backup/" + file1[i].getName();
                dataFile.setFilePath(filePath);
                DecimalFormat df = new DecimalFormat(".## ");
                dataFile.setFileSize(df.format(file1[i].length() / 1024000f));
                dataFiles.add(dataFile);
                records++;
                if (records >= rows) {
                    break;
                }
            }
            pageModel.setRecords(file1.length);
            pageModel.setPage(page);
            pageModel.setRows(dataFiles);
        }
        // 输出显示
        json = GridJq.toJSON(columns, pageModel);
        sendJSON(response, json);
    } catch (Exception e) {
        super.processException(response, e);
    }
}


public String getHost(){
    return host;
}


public void setReductionFile(File reductionFile){
    this.reductionFile = reductionFile;
}


}