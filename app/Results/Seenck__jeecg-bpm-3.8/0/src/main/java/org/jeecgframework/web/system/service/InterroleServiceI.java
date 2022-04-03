package org.jeecgframework.web.system.service;
 import java.io.Serializable;
import java.util.Set;
import org.jeecgframework.core.common.service.CommonService;
import org.jeecgframework.web.system.pojo.base.InterroleEntity;
public interface InterroleServiceI extends CommonService{


public Set<String> getOperationCodesByRoleIdAndruleDataId(String roleId,String interfaceId)
;

public Serializable save(InterroleEntity entity)
;

public int getUsersOfThisRole(String id)
;

public void delete(InterroleEntity entity)
;

public void saveOrUpdate(InterroleEntity entity)
;

}