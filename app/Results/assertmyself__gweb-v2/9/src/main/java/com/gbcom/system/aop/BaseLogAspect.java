package com.gbcom.system.aop;
 import java.util.Enumeration;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.beans.factory.annotation.Autowired;
import com.gbcom.system.daoservice.SysLogService;
import com.gbcom.system.domain.SysLog;
import com.gbcom.system.domain.SysUser;
import com.gbcom.system.manager.SysUserManager;
import com.gbcom.Interface.SysUserManager;
public class BaseLogAspect extends AbstractAspect{

@Autowired
 private SysLogService sysLogService;

@Autowired
 private  SysUserManager sysUserManager;


public void buildUserLog(UserLog userLog,SysLog sysLog){
    if (userLog == null) {
        return;
    }
    if (!StringUtils.isEmpty(userLog.eventType())) {
        sysLog.setEventType(userLog.eventType());
    }
    if (!StringUtils.isEmpty(userLog.moudle())) {
        sysLog.setMoudle(userLog.moudle());
    }
    sysLog.setLogType(userLog.LogType());
    sysLog.setMessage(userLog.description());
    // the result can be override
    sysLog.setResult(userLog.result());
}


public void log(SysLog sysLog){
    try {
        sysLogService.save(sysLog);
    } catch (Exception e) {
        logger.error("record log error!!!", e);
    }
}


public void buildSession(HttpServletRequest request,SysLog entity){
    Enumeration headers = request.getHeaderNames();
    SysUser sysUser = sysUserManager.getSysUser();
    if (sysUser != null) {
        entity.setUser(sysUser);
        entity.setIpAddress(request.getRemoteHost());
        entity.setSessionid(request.getRequestedSessionId());
        entity.setIeVersion(request.getHeader("User-Agent"));
        // 设置登入和登出时间
        entity.setPageUrl(request.getRequestURI());
    }
}


public void buildClassMethod(ProceedingJoinPoint joinPoint,SysLog sysLog){
    String targetName = joinPoint.getTarget().getClass().getSimpleName().replace("Controller", "");
    char[] chars = targetName.toCharArray();
    chars[0] = (char) (chars[0] + 32);
    targetName = new String(chars);
    String methodName = joinPoint.getSignature().getName();
    if (methodName.toLowerCase().contains("grid") || methodName.toLowerCase().contains("init")) {
        methodName = UserLog.USERLOG_EVENTTYPE_QUERY;
    } else if (methodName.toLowerCase().contains("modify") || methodName.toLowerCase().contains("save")) {
        methodName = UserLog.USERLOG_EVENTTYPE_MODIFY;
    } else if (methodName.toLowerCase().contains("view")) {
        methodName = UserLog.USERLOG_EVENTTYPE_VIEW;
    } else if (methodName.toLowerCase().contains("delete")) {
        methodName = UserLog.USERLOG_EVENTTYPE_DELETE;
    } else if (methodName.toLowerCase().contains("add")) {
        methodName = UserLog.USERLOG_EVENTTYPE_ADD;
    } else if (methodName.toLowerCase().contains("export")) {
        methodName = UserLog.USERLOG_EVENTTYPE_EXPORT;
    } else if (methodName.toLowerCase().contains("import")) {
        methodName = UserLog.USERLOG_EVENTTYPE_IMPORT;
    } else {
        methodName = UserLog.USERLOG_EVENTTYPE_UNKNOW;
    }
    sysLog.setMoudle(targetName);
    sysLog.setEventType(methodName);
}


}