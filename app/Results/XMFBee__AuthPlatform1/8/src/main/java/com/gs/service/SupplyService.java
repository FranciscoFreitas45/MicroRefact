package com.gs.service;
 import com.gs.bean.Checkin;
import com.gs.bean.Supply;
import com.gs.common.bean.Pager;
import java.util.List;
public interface SupplyService extends BaseService<String, Supply>{


public int queryNameByOne(String supplyName,String supplyId)
;

}