package org.jeecgframework.web.system.service;
 import java.util.List;
import java.util.Set;
import org.jeecgframework.web.system.pojo.base.DictEntity;
import org.jeecgframework.web.system.pojo.base.TSFunction;
import org.jeecgframework.web.system.pojo.base.TSIcon;
import org.jeecgframework.web.system.pojo.base.TSOperation;
import org.jeecgframework.web.system.pojo.base.TSType;
import org.jeecgframework.web.system.pojo.base.TSTypegroup;
import org.jeecgframework.web.system.pojo.base.TSUser;
import org.jeecgframework.core.common.service.CommonService;
public interface SystemService extends CommonService{


public void flushRoleFunciton(String id,TSFunction newFunciton)
;

public String getAuthFilterJS()
;

public void refleshTypesCach(TSType type)
;

public void initAllTypeGroups()
;

public Set<String> getDepartAuthGroupOperationSet(String groupId,String functionId,String type)
;

public void upTSIcons(TSIcon icon)
;

public List<TSOperation> getLoginOperationsByUserId(String userId,String functionId,String orgId,String processnodeId)
;

public Set<String> getLoginDataRuleIdsByUserId(String userId,String functionId,String orgId)
;

public void addLog(String LogContent,Short operatetype,Short loglevel)
;

public TSTypegroup getTypeGroupByCode(String typegroupCode)
;

public TSUser checkUserExits(TSUser user)
;

public List<DictEntity> queryDict(String dicTable,String dicCode,String dicText)
;

public void addDataLog(String tableName,String dataId,String dataContent)
;

public Set<String> getDataRuleIdsByRoleIdAndFunctionId(String roleId,String functionId)
;

public TSTypegroup getTypeGroup(String typegroupcode,String typgroupename)
;

public Set<String> getOperationCodesByRoleIdAndFunctionId(String roleId,String functionId)
;

public Set<String> getDepartAuthGroupDataRuleSet(String groupId,String functionId,String type)
;

public void refleshTypeGroupCach()
;

public String generateOrgCode(String id,String pid)
;

public void initAllTSIcons()
;

public String getFunctionIdByUrl(String requestPath,String menuPath)
;

public boolean loginUserIsHasMenuAuth(String requestPath,String clickFunctionId,String userid,String orgId)
;

public TSType getType(String typecode,String typename,TSTypegroup tsTypegroup)
;

public void refreshTypeGroupAndTypes()
;

public void delTSIcons(TSIcon icon)
;

}