package com.gs.dao;
 import com.gs.bean.AccessoriesBuy;
import com.gs.bean.User;
import com.gs.common.bean.Pager;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface AccessoriesBuyDAO extends BaseDAO<String, AccessoriesBuy>{


public List<AccessoriesBuy> queryByPagerDisable(Pager pager)
;

public int countByBlurred(AccessoriesBuy accessoriesBuy,User user)
;

public int countByDisable()
;

public List<AccessoriesBuy> queryByPayCondition(String start,String end,String companyId,String type)
;

public List<AccessoriesBuy> blurredQuery(Pager pager,AccessoriesBuy accessoriesBuy)
;

public List<AccessoriesBuy> queryByCondition(String start,String end,String companyId,String type)
;

}