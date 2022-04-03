package com.gbcom.update.client.service;
 import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import com.gbcom.system.utils.HttpClientUtil;
import com.gbcom.system.utils.JsonUtil;
import com.gbcom.update.client.manager.VersionInfoManager;
import com.gbcom.update.client.xml.UpdateClientContextManager;
import com.gbcom.update.common.VersionInfo;
import com.gbcom.update.common.exception.AutoUpdateException;
import com.gbcom.update.common.util.AutoUpdateUtil;
@Service("AutoUpdateService")
public class AutoUpdateClientService {

 private  Logger LOG;


public void restartServer(String path){
    LOG.info("restart tomcat !");
    try {
        if (System.getProperties().getProperty("os.name").equals("Linux")) {
            linuxRestartServer(path);
        } else {
            windowsRestartServer(path);
        }
    } catch (Exception e) {
        LOG.error(e.getMessage(), e);
        throw new AutoUpdateException("restart server exception :", e);
    }
}


public void downNewestVersion(HttpServletRequest request,String product){
    try {
        InputStream inputStream = HttpClientUtil.getAsStream(AutoUpdateUtil.getProperty("server.url.pre") + "/rest/update/downloadVer?product=" + product);
        if (inputStream == null) {
            throw new Exception("file that you want to download is not exist!");
        }
        String realPath = request.getSession().getServletContext().getRealPath("");
        realPath = AutoUpdateUtil.parsePath(realPath, AutoUpdateUtil.getProperty("client.temp.path"));
        File tempFile = new File(realPath);
        if (!tempFile.exists()) {
            tempFile.mkdir();
        }
        String filePath = realPath + File.separator + product + ".zip";
        filePath = filePath.replaceAll("\\\\", "/");
        long startTime = System.currentTimeMillis();
        LOG.info("client down file start; file path : " + filePath);
        AutoUpdateUtil.downloadAsStream(filePath, inputStream);
        long endTime = System.currentTimeMillis();
        long spend = (endTime - startTime) / 1000;
        LOG.info("client down file done! fiel path : " + filePath + ";; spend " + spend + " s");
    } catch (Exception e) {
        LOG.error(e.getMessage(), e);
        throw new AutoUpdateException("down newest version file exception :", e);
    }
}


public void stopWindowsServer(String path){
    String command = "cmd /c " + path + File.separator + "shutdown.bat";
    String filePath = path.substring(0, path.indexOf("bin") - 1);
    AutoUpdateUtil.executeCmd(command, filePath);
}


public VersionInfo checkedVer(){
    try {
        String product = UpdateClientContextManager.getInstance().getUpdateProduct();
        VersionInfo versionInfoCli = UpdateClientContextManager.getInstance().getVersionInfo(product);
        String json = HttpClientUtil.get(AutoUpdateUtil.getProperty("server.url.pre") + "/rest/update/checkedVer?product=" + product + "&curVer=" + versionInfoCli.getVersion() + "&verNo=" + versionInfoCli.getNo() + "&time=" + versionInfoCli.getDate());
        if (json == null || json.contains("404")) {
            return null;
        } else if (json.contains("404")) {
            throw new AutoUpdateException("无法连接到版本服务器");
        } else {
            versionInfoCli = JsonUtil.jsonToBean(json, VersionInfo.class);
            // 缓存最新版本信息
            VersionInfoManager.getInstance().put(product, versionInfoCli);
            return versionInfoCli;
        }
    } catch (Exception e) {
        LOG.error(e.getMessage(), e);
        throw new AutoUpdateException("check version exception :", e);
    }
}


public void codeUpdate(String path){
    try {
        AutoUpdateUtil.backup(path);
        AutoUpdateUtil.unzipFile(path);
        AutoUpdateUtil.recovery(path);
    } catch (Exception e) {
        LOG.error(e.getMessage(), e);
        throw new AutoUpdateException("code method update exception :", e);
    }
}


public void stopLinuxServer(String path){
    String command = path + File.separator + "shutdown.sh";
    AutoUpdateUtil.executeCmd(command, null);
}


public void windowsRestartServer(String path){
    if (path == null || path.equals("") || path.indexOf("bin") == -1) {
        throw new Exception("tomcat path wrong :" + path);
    }
    stopWindowsServer(path);
    String command = "cmd /c " + path + File.separator + "startup.bat";
    String filePath = path.substring(0, path.indexOf("bin") - 1);
    AutoUpdateUtil.executeCmd(command, filePath);
}


public void shUpdate(String shellPath){
    try {
        AutoUpdateUtil.executeCmd(shellPath, null);
    } catch (Exception e) {
        LOG.error(e.getMessage(), e);
        throw new AutoUpdateException("sh method update exception :", e);
    }
}


public void downNewestSql(HttpServletRequest request,String product,String time){
    try {
        InputStream inputStream = HttpClientUtil.getAsStream(AutoUpdateUtil.getProperty("server.url.pre") + "/rest/update/downloadSQL?product=" + product + "&time=" + time);
        if (inputStream == null) {
            throw new AutoUpdateException("you want to download file is not exsit!");
        }
        String realPath = request.getSession().getServletContext().getRealPath("");
        realPath = realPath.substring(0, realPath.indexOf("apache-tomcat"));
        String filePath = AutoUpdateUtil.parsePath(realPath, AutoUpdateUtil.getProperty("client.temp.path"));
        filePath = filePath + File.separator + AutoUpdateUtil.getProperty("project.sql.name") + ".zip";
        filePath = filePath.replaceAll("\\\\", "/");
        LOG.info("client down file start! fiel path : " + filePath);
        AutoUpdateUtil.downloadAsStream(filePath, inputStream);
        LOG.info("client down file done! fiel path : " + filePath);
    } catch (IOException e) {
        LOG.error(e.getMessage(), e);
        throw new AutoUpdateException("down newest sql file exception :", e);
    }
}


public void linuxRestartServer(String path){
    try {
        if (path == null || path.equals("")) {
            throw new Exception("tomcat path wrong :" + path);
        }
        stopLinuxServer(path);
        String command = path + File.separator + "startup.sh";
        AutoUpdateUtil.executeCmd(command, null);
    } catch (Exception e) {
        LOG.error(e.getMessage(), e);
        throw e;
    }
}


}