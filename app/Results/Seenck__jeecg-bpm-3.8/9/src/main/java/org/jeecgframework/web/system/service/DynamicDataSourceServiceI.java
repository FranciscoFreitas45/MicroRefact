package org.jeecgframework.web.system.service;
 import java.util.List;
import org.jeecgframework.web.system.pojo.base.DynamicDataSourceEntity;
public interface DynamicDataSourceServiceI {


public void refleshCache()
;

public List<DynamicDataSourceEntity> initDynamicDataSource()
;

public DynamicDataSourceEntity getDynamicDataSourceEntityForDbKey(String dbKey)
;

}