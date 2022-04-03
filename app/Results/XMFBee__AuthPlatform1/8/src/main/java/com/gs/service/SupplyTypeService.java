package com.gs.service;
 import com.gs.bean.SupplyType;
import com.gs.common.bean.Pager;
import java.util.List;
public interface SupplyTypeService extends BaseService<String, SupplyType>{


public List<SupplyType> queryByPagerDisable(Pager pager)
;

public int countByDisable()
;

public int queryNameByOne(String supplyTypeName,String supplyTypeId)
;

}