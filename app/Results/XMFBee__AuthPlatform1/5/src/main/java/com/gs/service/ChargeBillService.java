package com.gs.service;
 import com.gs.bean.ChargeBill;
import com.gs.bean.User;
import com.gs.common.bean.Pager;
import java.util.List;
public interface ChargeBillService extends BaseService<String, ChargeBill>{


public void updateCurrent(String id)
;

public void updateDate(String chargeBillId)
;

public List<ChargeBill> queryByOwner(Pager pager)
;

public int countByOwner(User frontUser)
;

public List<ChargeBill> queryByCondition(String start,String end,String userId,String maintainOrFix,String type)
;

}