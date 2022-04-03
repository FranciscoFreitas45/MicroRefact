package com.gs.dao;
 import com.gs.bean.AccessoriesType;
import com.gs.bean.User;
import com.gs.common.bean.Pager;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface AccessoriesTypeDAO extends BaseDAO<String, AccessoriesType>{


public List<AccessoriesType> queryByPagerDisable(Pager pager)
;

public int countByBlurred(AccessoriesType accessoriesType,User user)
;

public int countByDisable()
;

public List<AccessoriesType> queryTypeName(String companyId)
;

public int queryAccTypeNameOne(String accTypeName,String accTypeId)
;

public List<AccessoriesType> blurredQuery(Pager pager,AccessoriesType accessoriesType)
;

}