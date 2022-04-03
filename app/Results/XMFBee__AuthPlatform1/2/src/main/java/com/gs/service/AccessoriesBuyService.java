package com.gs.service;
 import com.gs.bean.AccessoriesBuy;
import com.gs.bean.User;
import com.gs.common.bean.Pager;
import org.apache.ibatis.annotations.Param;
import java.util.List;
public interface AccessoriesBuyService extends BaseService<String, AccessoriesBuy>{


public List<AccessoriesBuy> queryByPayCondition(String start,String end,String companyId,String type)
;

public List<AccessoriesBuy> queryByCondition(String start,String end,String companyId,String type)
;

}