package com.gs.dao;
 import com.gs.bean.SupplyType;
import com.gs.common.bean.Pager;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface SupplyTypeDAO extends BaseDAO<String, SupplyType>{


public List<SupplyType> queryByPagerDisable(Pager pager)
;

public int countByDisable()
;

public int queryNameByOne(String supplyTypeName,String supplyTypeId)
;

}