package com.gbcom.update.common.util;
 import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Properties;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import com.gbcom.system.mysql.SqlExportManager;
import com.gbcom.system.mysql.SqlImportManager;
import com.gbcom.system.utils.ZipFileUtil;
import com.gbcom.update.client.manager.VersionInfoManager;
import com.gbcom.update.client.xml.FileFilter;
import com.gbcom.update.client.xml.UpdateClientContext;
import com.gbcom.update.client.xml.UpdateClientContextManager;
import com.gbcom.update.common.VersionInfo;
public class AutoUpdateUtil {

 private  Logger LOG;

 private  String PROPERTIES_PATH;


public void executeCmd(String command,String filePath){
    LOG.info("command :" + command);
    Runtime r = Runtime.getRuntime();
    try {
        Process pcs = null;
        if (filePath == null) {
            pcs = r.exec(command, null, null);
        } else {
            pcs = r.exec(command, null, new File(filePath));
        }
        if (pcs == null) {
            return;
        }
        BufferedReader br = new BufferedReader(new InputStreamReader(pcs.getInputStream(), "gbk"));
        String str = "";
        while ((str = br.readLine()) != null) {
            LOG.info(str);
        }
        try {
            pcs.waitFor();
        } catch (InterruptedException e) {
            LOG.error("processes was interrupted");
        }
        int ret = pcs.exitValue();
        if (ret == 0) {
            LOG.info("excute command success ! command : " + command);
        } else {
            LOG.info("excute command failed ! command : " + command);
        }
        if (br != null) {
            br.close();
        }
        if (r != null) {
            r.gc();
        }
    } catch (IOException e) {
        LOG.error(e.getMessage(), e);
    }
}


public void backup(String path){
    String product = UpdateClientContextManager.getInstance().getUpdateProduct();
    String proName = UpdateClientContextManager.getInstance().getVersionInfo(product).getName();
    String packagePath = AutoUpdateUtil.parsePath(path, getProperty("client.temp.path"));
    String curProBackupPath = packagePath + File.separator + proName + ".bak.file";
    String curDBBackupPath = packagePath + File.separator + proName + ".bak.sql";
    curProBackupPath = curProBackupPath.replaceAll("\\\\", "/");
    curDBBackupPath = curDBBackupPath.replaceAll("\\\\", "/");
    backupCurVersion(path, curProBackupPath);
    backupCurDB(curDBBackupPath);
}


@Override
public int compare(File f1,File f2){
    return f1.getName().compareTo(f2.getName());
}


public String getProperty(String key){
    InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream(PROPERTIES_PATH);
    Properties prop = new Properties();
    try {
        prop.load(in);
        return prop.get(key).toString();
    } catch (IOException e) {
        LOG.error(e.getMessage(), e);
        return null;
    }
}


public String parsePath(String path,String parten){
    File filePath = new File(path);
    if (!filePath.exists()) {
        return path;
    }
    String[] strs = parten.split("/");
    String temp = "";
    for (int i = 0; i < strs.length; i++) {
        if (strs[i].equals("..")) {
            filePath = filePath.getParentFile();
        } else {
            temp += temp + File.separator + strs[i];
        }
    }
    String result = filePath.getAbsolutePath() + temp;
    result = result.replaceAll("\\\\", "/");
    return result;
}


public void unzipNewProFile(String path){
    try {
        String product = UpdateClientContextManager.getInstance().getUpdateProduct();
        String proName = UpdateClientContextManager.getInstance().getVersionInfo(product).getName();
        String newestProTempPath = AutoUpdateUtil.parsePath(path, getProperty("client.temp.path"));
        String newestZipProPath = newestProTempPath + File.separator + proName + ".zip";
        newestZipProPath = newestZipProPath.replaceAll("\\\\", "/");
        File newestZipProFile = new File(newestZipProPath);
        LOG.info("unzip newest version ! outpath :" + newestProTempPath);
        ZipFileUtil.unZipFiles(newestZipProFile, path + File.separator);
        LOG.info("unzip newest verion done ! zipFilePath: " + newestZipProPath);
    } catch (IOException e) {
        LOG.error(e.getMessage(), e);
        throw e;
    }
}


public void overideCurVersion(String src,String dst){
    try {
        LOG.info("excute overide current version !");
        File srcFile = new File(src);
        File dstFile = new File(dst);
        if (!srcFile.exists()) {
            throw new Exception("source file is not exist ! please check ; source path :" + src);
        }
        if (!dstFile.exists()) {
            dstFile.mkdir();
        }
        FileUtils.copyDirectory(new File(src), new File(dst));
        LOG.info("overide current version done ! ; zipFilePath : " + src);
    } catch (Exception e) {
        LOG.error(e.getMessage(), e);
        throw e;
    }
}


public void main(String[] args){
    try {
        AutoUpdateUtil.replaceSpeFile("E:\\java\\apache-tomcat-6.0.41\\temp\\ccsv3.bak.file", "E:\\java\\apache-tomcat-6.0.41\\webapps\\ccsv3");
    } catch (Exception e) {
        e.printStackTrace();
    }
}


public void recovery(String path){
    String product = UpdateClientContextManager.getInstance().getUpdateProduct();
    String proName = UpdateClientContextManager.getInstance().getVersionInfo(product).getName();
    String packagePath = AutoUpdateUtil.parsePath(path, getProperty("client.temp.path"));
    // String tempPath = AutoUpdateUtil.parsePath(path,
    // getProperty("client.temp.path"));
    String curProBackupPath = packagePath + File.separator + proName + ".bak.file";
    // String newestProTempPath = tempPath + File.separator;
    // String newestProPath = newestProTempPath + proName;
    // overideCurVersion(newestProPath, path);
    replaceSpeFile(curProBackupPath, path);
    // updateOemFile();
    // updateClientFile();
    excuteNewSql();
}


public void backupCurVersion(String src,String dst){
    LOG.info("excute backup current version !");
    try {
        File srcFile = new File(src);
        if (!srcFile.exists()) {
            throw new Exception("source file is not exist ! please check ; source path :" + src);
        }
        File dstFile = new File(dst);
        if (!dstFile.exists()) {
            dstFile.mkdir();
        }
        FileUtils.copyDirectory(new File(src), new File(dst));
        LOG.info("backup current version has done ! ; backupPath: " + dst);
    } catch (IOException e) {
        LOG.error(e.getMessage(), e);
        throw e;
    }
}


@SuppressWarnings("unused")
public void updateOemFile(){
    String product = UpdateClientContextManager.getInstance().getUpdateProduct();
    VersionInfo info = VersionInfoManager.getInstance().get(product);
    String newOemDate = info.getDate();
    String newNo = info.getNo();
    String newVer = info.getVersion();
// Sys.oemM().getOem().setDate(newOemDate);
// Sys.oemM().getOem().setBuild(newNo);
// Sys.oemM().getOem().getVendor().setVersion(newVer);
// Sys.oemM().save();
}


public void backupCurDB(String path){
    try {
        LOG.info("excute backup current database !");
        SqlExportManager sqlService = new SqlExportManager();
        sqlService.exportSql(path);
        LOG.info("backup current database has done ! ; backup path: " + path);
    } catch (IOException e) {
        LOG.error(e.getMessage(), e);
        throw e;
    }
}


public void replaceSpeFile(String srcDir,String dstDir){
    try {
        LOG.info("excute replaceSpeFile !");
        String product = UpdateClientContextManager.getInstance().getUpdateProduct();
        List<FileFilter> fileFilters = UpdateClientContextManager.getInstance().getFileFilter(product);
        List<File> includeFiles = new ArrayList<File>();
        List<File> excludeFiles = new ArrayList<File>();
        for (FileFilter ff : fileFilters) {
            if (ff.getInclude() != null && !ff.getInclude().equals("")) {
                String include = srcDir + ff.getInclude();
                includeFiles.add(new File(include));
            }
            if (ff.getExclude() != null && !ff.getExclude().equals("")) {
                String exclude = srcDir + ff.getExclude();
                excludeFiles.add(new File(exclude));
            }
        }
        AutoUpdateFileFilter fileFilter = new AutoUpdateFileFilter();
        if (includeFiles.size() != 0) {
            fileFilter.setIncludeFiles(includeFiles);
        }
        if (excludeFiles.size() != 0) {
            fileFilter.setExcludeFiles(excludeFiles);
        }
        File srcFile = new File(srcDir);
        File dstFile = new File(dstDir);
        if (!srcFile.exists()) {
            throw new Exception("source file is not exist :" + srcDir);
        }
        if (!dstFile.exists()) {
            dstFile.mkdir();
        }
        List<File> fileList = new ArrayList<File>();
        List<File> files = AutoUpdateFileFilter.filterFile(srcFile, fileFilter, fileList);
        String proName = UpdateClientContextManager.getInstance().getVersionInfo(product).getName();
        for (int i = 0; i < files.size(); i++) {
            File srcFileFile = files.get(i);
            String srcFilePath = srcFileFile.getAbsolutePath();
            String dstFilePath = dstFile.getAbsolutePath();
            dstFilePath = dstFilePath.substring(0, dstFilePath.indexOf(proName) + proName.length()) + srcFilePath.substring(srcFilePath.indexOf(proName + ".bak.file") + (proName + ".bak.file").length());
            File dstFileFile = new File(dstFilePath);
            if (!dstFileFile.exists()) {
                dstFileFile.createNewFile();
            }
            FileUtils.copyFile(srcFileFile, dstFileFile);
        }
        LOG.info("replaceSpeFile has done ! ; replaceFile path : " + srcDir);
    } catch (Exception e) {
        LOG.error(e.getMessage(), e);
        throw e;
    }
}


public void excuteNewSql(){
    try {
        String product = UpdateClientContextManager.getInstance().getUpdateProduct();
        String time = UpdateClientContextManager.getInstance().getVersionInfo(product).getDate();
        List<File> fileList = getNewSQL(time);
        Collections.sort(fileList, new Comparator<File>() {

            @Override
            public int compare(File f1, File f2) {
                return f1.getName().compareTo(f2.getName());
            }
        });
        for (File file : fileList) {
            if (file.exists()) {
                File[] fs = file.listFiles();
                for (File f : fs) {
                    excuteSql(f.getAbsolutePath());
                }
            }
        }
    } catch (Exception e) {
        LOG.error(e.getMessage(), e);
        throw e;
    }
}


public void download(HttpServletResponse response,String path){
    InputStream in = null;
    ServletOutputStream out = null;
    try {
        File file = new File(path);
        if (!file.exists()) {
            throw new Exception("file is not exsit !");
        }
        in = new FileInputStream(file);
        out = response.getOutputStream();
        byte[] b = new byte[1024];
        int len = 0;
        while ((len = in.read(b)) > 0) {
            out.write(b, 0, len);
        }
        LOG.info("down file done! file path is : " + path);
    } catch (Exception e) {
        LOG.error(e.getMessage(), e);
    } finally {
        try {
            if (in != null) {
                in.close();
            }
            if (out != null) {
                out.close();
            }
        } catch (Exception e2) {
            LOG.error(e2.getMessage(), e2);
        }
    }
}


@SuppressWarnings("unused")
public void synConfigFile(){
    try {
        LOG.info("update local client_xml file start!");
        String config = Thread.currentThread().getContextClassLoader().getResource("config/update/client_site.xml").getPath();
        String product = UpdateClientContextManager.getInstance().getUpdateProduct();
        VersionInfo curInfo = UpdateClientContextManager.getInstance().getVersionInfo(product);
        VersionInfo newInfo = VersionInfoManager.getInstance().get(product);
        Field[] fields = curInfo.getClass().getDeclaredFields();
        for (Field field : fields) {
            String fieldName = field.getName();
            String methodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
            Method method = curInfo.getClass().getDeclaredMethod(methodName);
            String curValue = method.invoke(curInfo).toString();
            String newValue = method.invoke(newInfo).toString();
            replaceFileContent(config, curValue, newValue);
        }
        LOG.info("update local client_xml file end!");
    } catch (Exception e) {
        LOG.error(e.getMessage(), e);
        throw e;
    }
}


public void excuteSql(String path){
    try {
        LOG.info("excute sql !");
        SqlImportManager cfgSqlImportService = new SqlImportManager();
        cfgSqlImportService.importSql(path);
        LOG.info("excute sql done ! sql path : " + path);
    } catch (IOException e) {
        LOG.error(e.getMessage(), e);
        throw e;
    }
}


public List<File> getNewSQL(String time){
    try {
        String incSqlPath = "/sql/";
        incSqlPath = Thread.currentThread().getContextClassLoader().getResource(incSqlPath).getPath();
        File incSqlFile = new File(incSqlPath);
        if (!incSqlFile.exists()) {
            throw new Exception("file is not exist !");
        }
        File[] sqlFiles = incSqlFile.listFiles();
        List<File> fileList = new ArrayList<File>();
        for (File sqlf : sqlFiles) {
            if (sqlf.isDirectory()) {
                String fileName = sqlf.getName();
                if (fileName.compareToIgnoreCase(time) > 0) {
                    fileList.add(sqlf);
                }
            }
        }
        return fileList;
    } catch (Exception e) {
        LOG.error(e.getMessage(), e);
        throw e;
    }
}


public void downloadAsStream(String path,InputStream in){
    OutputStream out = null;
    try {
        File file = new File(path);
        if (!file.exists()) {
            file.createNewFile();
        }
        if (in == null) {
            return;
        }
        out = new FileOutputStream(file);
        byte[] b = new byte[1024];
        int len = 0;
        while ((len = in.read(b)) > 0) {
            out.write(b, 0, len);
        }
    } catch (IOException e) {
        LOG.error(e.getMessage(), e);
        throw e;
    } finally {
        try {
            if (in != null) {
                in.close();
            }
            if (out != null) {
                out.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
            LOG.error(e.getMessage(), e);
        }
    }
}


@SuppressWarnings("unused")
public void updateClientFile(){
    String product = UpdateClientContextManager.getInstance().getUpdateProduct();
    VersionInfo info = VersionInfoManager.getInstance().get(product);
    String newDate = info.getDate();
    String newNo = info.getNo();
    String newVer = info.getVersion();
    UpdateClientContext updateClientContext = UpdateClientContextManager.getInstance().getUpdateClientContext();
    updateClientContext.getUpdateClient().setDate(newDate);
    updateClientContext.getUpdateClient().setNo(newNo);
    updateClientContext.getUpdateClient().setVersion(newVer);
    UpdateClientContextManager.getInstance().save();
}


public void unzipFile(String path){
    try {
        unzipNewProFile(path);
    } catch (IOException e) {
        LOG.error(e.getMessage(), e);
        throw e;
    }
}


public void replaceFileContent(String path,String oldStr,String newStr){
    try {
        LOG.info("replace oem content start!");
        File file = new File(path);
        InputStreamReader isr = new InputStreamReader(new FileInputStream(file));
        BufferedReader br = new BufferedReader(isr);
        StringBuffer buff = new StringBuffer();
        String temp = br.readLine();
        while (temp != null) {
            if (temp.contains(oldStr)) {
                String left = temp.substring(0, temp.indexOf(oldStr));
                String right = temp.substring(temp.indexOf(oldStr) + oldStr.length(), temp.length());
                temp = left + newStr + right;
            }
            buff.append(temp);
            buff = buff.append(System.getProperty("line.separator"));
            temp = br.readLine();
        }
        br.close();
        FileOutputStream fos = new FileOutputStream(file);
        PrintWriter pw = new PrintWriter(fos);
        pw.write(buff.toString().toCharArray());
        pw.flush();
        pw.close();
        LOG.info("replace oem content end!");
    } catch (FileNotFoundException e) {
        LOG.error(e.getMessage(), e);
    } catch (IOException e) {
        LOG.error(e.getMessage(), e);
    }
}


}