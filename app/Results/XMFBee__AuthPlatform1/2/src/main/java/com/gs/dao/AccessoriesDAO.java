package com.gs.dao;
 import com.gs.bean.Accessories;
import com.gs.bean.User;
import com.gs.common.bean.Pager;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface AccessoriesDAO extends BaseDAO<String, Accessories>{


public int updateCount(int accCount,String accId)
;

public Accessories queryByName(String accName)
;

public int countByBlurred(Accessories accessories,User user)
;

public int reduceCount(int accCount,String accId)
;

public List<Accessories> queryByIdPager(String id,Pager pager)
;

public List<Accessories> blurredQuery(Pager pager,Accessories accessories)
;

public List<Accessories> queryByCondition(String start,String end,String companyId,String accTypeId,String type)
;

}