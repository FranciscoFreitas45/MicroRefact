package org.jeecgframework.web.system.service;
 import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.jeecgframework.core.common.service.CommonService;
import org.jeecgframework.web.system.pojo.base.TSFunction;
import org.jeecgframework.web.system.pojo.base.TSUser;
public interface UserService extends CommonService{


public String getUserRole(TSUser user)
;

public void pwdInit(TSUser user,String newPwd)
;

public String trueDel(TSUser user)
;

public boolean isInBlackList(String ip)
;

public void saveLoginUserInfo(HttpServletRequest req,TSUser u,String orgId)
;

public int getUsersOfThisRole(String id)
;

public Map<String,TSFunction> getLoginUserFunction(String userId)
;

public TSUser checkUserExits(String username,String password)
;

public String getShortcutPrimaryMenu(List<TSFunction> primaryMenu)
;

public void saveOrUpdate(TSUser user,String[] orgIds,String[] roleIds)
;

public Map<Integer,List<TSFunction>> getFunctionMap(String userid)
;

public String getShortcutPrimaryMenuDiy(List<TSFunction> primaryMenu)
;

}