package org.jeecgframework.web.system.service;
 import java.io.Serializable;
import org.jeecgframework.core.common.service.CommonService;
import org.jeecgframework.web.system.enums.InterfaceEnum;
import org.jeecgframework.web.system.pojo.base.InterfaceRuleDto;
import org.jeecgframework.web.system.pojo.base.TSInterfaceEntity;
public interface TSInterfaceServiceI extends CommonService{


public Serializable save(TSInterfaceEntity entity)
;

public void delete(TSInterfaceEntity entity)
;

public InterfaceRuleDto getInterfaceRuleByUserNameAndCode(String userName,InterfaceEnum interfaceEnum)
;

public void saveOrUpdate(TSInterfaceEntity entity)
;

}