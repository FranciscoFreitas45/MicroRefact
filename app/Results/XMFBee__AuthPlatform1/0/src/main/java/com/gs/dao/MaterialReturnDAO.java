package com.gs.dao;
 import com.gs.bean.Accessories;
import com.gs.bean.MaterialReturn;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface MaterialReturnDAO extends BaseDAO<String, MaterialReturn>{


public List<MaterialReturn> queryByCondition(String start,String end,String companyId,String accTypeId,String type)
;

}