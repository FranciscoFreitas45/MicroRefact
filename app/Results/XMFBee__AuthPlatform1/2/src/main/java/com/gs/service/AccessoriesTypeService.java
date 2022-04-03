package com.gs.service;
 import com.gs.bean.AccessoriesType;
import com.gs.common.bean.Pager;
import org.apache.ibatis.annotations.Param;
import java.util.List;
public interface AccessoriesTypeService extends BaseService<String, AccessoriesType>{


public int countByDisable()
;

public List<AccessoriesType> queryTypeName(String companyId)
;

public int queryAccTypeNameOne(String accTypeName,String accTypeId)
;

}