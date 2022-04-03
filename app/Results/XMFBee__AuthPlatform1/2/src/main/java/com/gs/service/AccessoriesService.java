package com.gs.service;
 import com.gs.bean.Accessories;
import com.gs.common.bean.Pager;
import org.apache.ibatis.annotations.Param;
import java.util.List;
public interface AccessoriesService extends BaseService<String, Accessories>{


public int updateCount(int accCount,String accId)
;

public Accessories queryByName(String accName)
;

public int reduceCount(int accCount,String accId)
;

public List<Accessories> queryByIdPager(String id,Pager pager)
;

public List<Accessories> queryByCondition(String start,String end,String companyId,String accTypeId,String type)
;

}