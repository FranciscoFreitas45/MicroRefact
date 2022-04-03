package com.gbcom.update.server.controller;
 import java.io.Serializable;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.gbcom.system.aop.UserLog;
import com.gbcom.system.manager.SysLogManager;
import com.gbcom.system.utils.JsonUtil;
import com.gbcom.update.common.VersionInfo;
import com.gbcom.update.common.exception.AutoUpdateException;
import com.gbcom.update.server.service.AutoUpdateServerService;
import com.gbcom.update.server.xml.UpdateServerContextManager;
import com.hc.core.controller.BaseCRUDActionController;
import com.gbcom.Interface.SysLogManager;
@Controller
public class AutoUpdateServerController extends BaseCRUDActionController<Serializable>{

@Autowired
 private  SysLogManager sysLogManager;

 private  Logger LOG;

@Autowired
 private  AutoUpdateServerService autoUpgradeServerService;


@RequestMapping(method = RequestMethod.GET, value = "/update/checkedVer")
public void checkedVer(HttpServletRequest request,HttpServletResponse response,String product,String curVer,String verNo,String time){
    try {
        boolean result = autoUpgradeServerService.isNewestVersion(product, curVer, verNo, time);
        if (result) {
            sendJSON(response, JsonUtil.beanToJSON(null));
        } else {
            VersionInfo versionInfo = UpdateServerContextManager.getInstance().getVersionInfo(product);
            sendJSON(response, JsonUtil.beanToJSON(versionInfo));
        }
        sysLogManager.log("admin", request.getRequestURI(), request.getRemoteHost(), request.getRequestedSessionId(), UserLog.USERLOG_LOGTYPE_BUSSINESS, "AutoUpdateServer", UserLog.USERLOG_EVENTTYPE_UPDATE, "检查版本");
    } catch (AutoUpdateException e) {
        LOG.error(e.getMessage(), e);
        sendJSON(response, JsonUtil.beanToJSON(null));
    }
}


@RequestMapping(method = RequestMethod.GET, value = "/update/downloadVer")
public void downloadVer(HttpServletRequest request,HttpServletResponse response,String product){
    try {
        autoUpgradeServerService.downNewestVersion(response, request, product);
        sysLogManager.log("admin", request.getRequestURI(), request.getRemoteHost(), request.getRequestedSessionId(), UserLog.USERLOG_LOGTYPE_BUSSINESS, "AutoUpdateServer", UserLog.USERLOG_EVENTTYPE_UPDATE, "下载版本");
    } catch (AutoUpdateException e) {
        LOG.error(e.getMessage(), e);
    }
}


}