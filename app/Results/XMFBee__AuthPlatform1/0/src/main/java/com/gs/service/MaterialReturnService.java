package com.gs.service;
 import com.gs.bean.Accessories;
import com.gs.bean.MaterialReturn;
import org.apache.ibatis.annotations.Param;
import java.util.List;
public interface MaterialReturnService extends BaseService<String, MaterialReturn>{


public List<MaterialReturn> queryByCondition(String start,String end,String companyId,String accTypeId,String type)
;

}