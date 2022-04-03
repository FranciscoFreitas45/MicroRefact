package com.gbcom.update.client.controller;
 import java.io.Serializable;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.gbcom.system.aop.UserLog;
import com.gbcom.system.manager.SysLogManager;
import com.gbcom.update.client.manager.VersionInfoManager;
import com.gbcom.update.client.service.AutoUpdateClientService;
import com.gbcom.update.client.xml.UpdateClientContextManager;
import com.gbcom.update.common.VersionInfo;
import com.gbcom.update.common.exception.AutoUpdateException;
import com.gbcom.update.common.util.AutoUpdateUtil;
import com.hc.core.controller.BaseCRUDActionController;
import com.gbcom.Interface.SysLogManager;
@Controller
public class AutoUpdateClientController extends BaseCRUDActionController<Serializable>{

 private  Logger LOG;

@Autowired
 private  AutoUpdateClientService autoUpdateClientService;

@Autowired
 private  SysLogManager sysLogManager;


@RequestMapping
public void restartServer(HttpServletRequest request,HttpServletResponse response){
    try {
        String proRealPath = request.getSession().getServletContext().getRealPath("");
        String tomcatBinPath = proRealPath.substring(0, proRealPath.indexOf("webapps")) + "bin";
        autoUpdateClientService.restartServer(tomcatBinPath);
    } catch (Exception e) {
        LOG.error(e.getMessage(), e);
        String command = "service tomcat6 restart";
        try {
            LOG.info("私有云，执行service tomcat6 restart重启服务器");
            AutoUpdateUtil.executeCmd(command, null);
        } catch (Exception e1) {
            LOG.error("无法重启服务器" + e.getMessage(), e);
        }
    }
}


@RequestMapping
public void autoUpdate(HttpServletRequest request,HttpServletResponse response){
    try {
        // 1.检查版本
        VersionInfo versionInfo = autoUpdateClientService.checkedVer();
        if (versionInfo == null) {
            sendFailureJSON(response, "当前版本为最新版本");
            return;
        }
        // 2.下载最新版本
        autoUpdateClientService.downNewestVersion(request, versionInfo.getProduct());
        // 升级
        String updateMethod = UpdateClientContextManager.getInstance().getVersionInfo(versionInfo.getProduct()).getMethod();
        if (updateMethod == null) {
            return;
        }
        if (updateMethod.equals("sh")) {
            autoUpdateClientService.shUpdate("");
        } else {
            String proRealPath = request.getSession().getServletContext().getRealPath("");
            autoUpdateClientService.codeUpdate(proRealPath);
            sendSuccessJSON(response, "升级成功，稍候将重启服务器！");
        }
        sysLogManager.log("admin", request.getRequestURI(), request.getRemoteHost(), request.getRequestedSessionId(), UserLog.USERLOG_LOGTYPE_BUSSINESS, "AutoUpdateClient", UserLog.USERLOG_EVENTTYPE_UPDATE, "升级web版本");
    } catch (Exception e) {
        LOG.error(e.getMessage(), e);
        sendFailureJSON(response, "操作失败！");
        return;
    }
}


@RequestMapping
public String verCheck(Model model){
    VersionInfo curVerInfo = null;
    VersionInfo newVerInfo = null;
    boolean newCur = false;
    try {
        VersionInfo versionInfo = autoUpdateClientService.checkedVer();
        String product = UpdateClientContextManager.getInstance().getUpdateProduct();
        curVerInfo = UpdateClientContextManager.getInstance().getVersionInfo(product);
        if (versionInfo == null) {
            newCur = true;
            newVerInfo = curVerInfo;
        } else {
            newCur = false;
            newVerInfo = VersionInfoManager.getInstance().get(product);
        }
        model.addAttribute("curVerInfo", curVerInfo);
        model.addAttribute("newVerInfo", newVerInfo);
        model.addAttribute("newCur", newCur);
        return "view/system/sysInfo/autoUpdate";
    } catch (Exception e) {
        newCur = true;
        newVerInfo = new VersionInfo();
        newVerInfo.setName("无法连接版本服务器");
        newVerInfo.setDate("无法连接版本服务器");
        newVerInfo.setMethod("无法连接版本服务器");
        newVerInfo.setNo("无法连接版本服务器");
        newVerInfo.setProduct("无法连接版本服务器");
        newVerInfo.setVersion("无法连接版本服务器");
        model.addAttribute("newVerInfo", newCur);
        return "view/system/sysInfo/autoUpdate";
    }
}


@RequestMapping
public void updateCheck(HttpServletRequest request,HttpServletResponse response){
    try {
        String product = UpdateClientContextManager.getInstance().getUpdateProduct();
        VersionInfo versionInfo = VersionInfoManager.getInstance().get(product);
        if (versionInfo == null) {
            VersionInfo info = autoUpdateClientService.checkedVer();
            if (info != null) {
                sendSuccessJSON(response, "true");
            } else {
                sendFailureJSON(response, "false");
            }
        } else {
            sendSuccessJSON(response, "true");
        }
    } catch (AutoUpdateException e) {
        LOG.error(e.getMessage(), e);
        sendFailureJSON(response, "false");
    }
}


}