package com.gbcom.system.manager;
 import com.gbcom.system.daoservice.SysLogService;
import com.gbcom.system.daoservice.SysUserService;
import com.gbcom.system.domain.SysLog;
import com.gbcom.system.domain.SysUser;
import com.gbcom.system.utils.Constants;
import com.hc.core.security.count.UserLogger;
import com.hc.core.utils.DateTimeHelper;
import com.hc.core.utils.FormatUtils;
import com.hc.core.webservice.security.WSLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import com.gbcom.Interface.SysUserService;
import com.gbcom.Interface.SysUserManager;
import com.gbcom.Interface.SysCodeManager;
@Service("userLogger")
public class SysLogManager implements UserLogger,WSLogService{

@Autowired
 private  SysLogService sysLogService;

@Autowired
 private  SysUserService sysUserService;

@Autowired
 private  SysUserManager sysUserManager;

@Autowired
 private  SysCodeManager sysCodeManager;


public void log(String username,String pageUrl,String userIp,String sessionId){
    this.log(username, pageUrl, userIp, sessionId, Constants.LOG_TYPE_WS, null, null, null);
}


public void deleteLog(Long userId){
    String hql = "from SysLog where user.id={0}";
    List<SysLog> sysLogs = sysLogService.findByQuery(FormatUtils.format(hql, userId));
    for (SysLog sysLog : sysLogs) {
        sysLogService.delete(sysLog);
    }
}


public void save(SysUser loginUser,String ipAddress,String logType){
    SysLog bean = new SysLog();
    bean.setIpAddress(ipAddress);
    // bean.setLogType(sysCodeManager.getCodeDetailByCode(
    // Constants.LOG_TYPE_CODE, logType));
    bean.setLogType(logType);
    bean.setUser(loginUser);
    if (logType.equals(Constants.LOG_TYPE_LOGIN)) {
        bean.setEnterTime(DateTimeHelper.getTimestamp());
    } else if (logType.equals(Constants.LOG_TYPE_LOGOUT)) {
        bean.setOutTime(DateTimeHelper.getTimestamp());
    }
    this.save(bean);
}


public List<SysLog> getTodayLoginCount(String time){
    Long userId = sysUserManager.getSysUser().getId();
    String hql = "from SysLog where enterTime >= '" + time + "' and user.id = " + userId;
    return sysLogService.findByQuery(hql);
}


}