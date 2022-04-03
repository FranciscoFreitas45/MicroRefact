package com.gbcom.system.utils;
 import java.util.List;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.gbcom.system.domain.SysUser;
import com.gbcom.system.manager.SysAreaManager;
import com.gbcom.system.manager.UserSessionManager;
import com.hc.core.utils.SpringUtils;
import com.gbcom.DTO.SysAreaManager;
public class UserSessionUtils {

 private  Logger log;

 public  String USER_STATUS_VALID;

 public  String USER_STATUS_INVALID;

 public  String USER_STATUS_LOCKED;

 private  UserSessionUtils instance;

/**
 * 构造函数
 */
private UserSessionUtils() {
}
public SysUser getLoginedUser(){
    return getUserSession().getLoginedUser();
}


public Boolean isUserInvalid(SysUser user){
    if (StringUtils.equals(user.getStatus(), this.USER_STATUS_INVALID)) {
        return Boolean.TRUE;
    } else {
        return Boolean.FALSE;
    }
}


public String getDevDomain(){
    HttpSession session = ServerContext.getSession();
    if (session == null) {
        // exception
        return "NoSession";
    }
    String domain = (String) session.getAttribute("domain");
    return domain;
}


public Boolean isUserValid(SysUser user){
    if (StringUtils.equals(user.getStatus(), this.USER_STATUS_VALID)) {
        return Boolean.TRUE;
    } else {
        return Boolean.FALSE;
    }
}


public Long getLoginedUserId(){
    return getUserSession().getLoginedUserId();
}


public void initDevDomain(SysUser user){
/*
		ServerContext.getSession().setAttribute("hasLogin", true);

		String manageDev = "";
		int groupType = 0;
		for (SysUserRole userRole : user.getSysUserRoles()) {
			groupType = Integer.valueOf(userRole.getRole().getId().toString());
			break;
		}
		switch (groupType) {

		case 1:
		case 2:
		case 3:
		case 4:
			//
			manageDev = "ALL,";
			break;
		case 5:
			manageDev = getManageDev(GUserType.CUSTOMER, user.getLoginName());
			break;
		case 6:
			manageDev = getManageDev(GUserType.AGENT, user.getLoginName());
			break;
		case 7:
			manageDev = getManageDev(GUserType.CGMERATE, user.getLoginName());
			break;
		case 8:
		case 9:
			manageDev = getManageDev();
			break;
		default:
			manageDev = "ALL,";
		}
		String res = manageDev.substring(0, manageDev.length() - 1);
		ServerContext.getSession().setAttribute("domain", res);

	*/
}


public String getManageDev(){
    SysAreaManager sysAreaManager = (SysAreaManager) SpringUtils.getBean("sysAreaManager");
    List<String> neIds = sysAreaManager.sysAreaHotsUnderSysArea();
    String keys = "";
    for (String ne : neIds) {
        keys += ne + ",";
    }
    return keys.contains(",") ? keys : ",";
}


public UserSessionUtils getInstance(){
    if (instance == null) {
        instance = new UserSessionUtils();
    }
    return instance;
}


public UserSessionManager getUserSession(){
    try {
        // return (UserSessionManager)
        // getApplicationContext().getBean("userSessionManager");
        return (UserSessionManager) SpringUtils.getBean("userSessionManager");
    } catch (Exception e) {
        log.error("error", e);
    }
    return null;
}


public Boolean isUserLocked(SysUser user){
    if (StringUtils.equals(user.getStatus(), this.USER_STATUS_LOCKED)) {
        return Boolean.TRUE;
    } else {
        return Boolean.FALSE;
    }
}


}