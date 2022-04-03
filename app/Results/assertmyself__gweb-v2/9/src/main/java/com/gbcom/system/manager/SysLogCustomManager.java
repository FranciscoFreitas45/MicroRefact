package com.gbcom.system.manager;
 import java.util.Enumeration;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gbcom.system.daoservice.SysLogService;
import com.gbcom.system.domain.SysLog;
import com.gbcom.system.domain.SysUser;
import com.gbcom.system.utils.Constants;
import com.hc.core.utils.DateTimeHelper;
import com.gbcom.Interface.SysUserManager;
@Service
public class SysLogCustomManager {

 private  Logger logger;

@Autowired
 private  SysLogService sysLogService;

@Autowired
 private  SysUserManager sysUserManager;


@SuppressWarnings("unchecked")
public void log(HttpServletRequest request,String logType){
    Enumeration headers = request.getHeaderNames();
    System.out.println("-----" + request.getHeader("ACCEPT"));
    while (headers.hasMoreElements()) {
        String key = headers.nextElement().toString();
        System.out.println("HEAD---- KEY=" + key + " ;; value=" + request.getHeader(key));
    }
    try {
        if (StringUtils.isEmpty(logType)) {
            logType = Constants.LOG_TYPE_BUSSINESS;
        }
        SysUser sysUser = sysUserManager.getSysUser();
        if (sysUser != null) {
            SysLog entity = new SysLog();
            entity.setUser(sysUser);
            entity.setIpAddress(request.getRemoteHost());
            entity.setSessionid(request.getRequestedSessionId());
            // entity.setLogType(sysCodeManager.getCodeDetailByCode(
            // Constants.LOG_TYPE_CODE, logType));
            entity.setLogType(logType);
            entity.setIeVersion(request.getHeader("User-Agent"));
            // 设置登入和登出时间
            entity.setPageUrl(request.getRequestURI());
            if (logType.equals(Constants.LOG_TYPE_LOGIN)) {
                entity.setEnterTime(DateTimeHelper.getTimestamp());
                entity.setPageUrl("/mainPage/index.do");
            } else if (logType.equals(Constants.LOG_TYPE_LOGOUT)) {
                entity.setOutTime(DateTimeHelper.getTimestamp());
                entity.setPageUrl("/j_spring_security_logout");
            }
            sysLogService.save(entity);
        }
    } catch (Exception e) {
        logger.error("log error!!!!!", e);
    }
}


}