package com.gs.dao;
 import com.gs.bean.ChargeBill;
import com.gs.bean.User;
import com.gs.common.bean.Pager;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface ChargeBillDAO extends BaseDAO<String, ChargeBill>{


public void updateCurrent(String id)
;

public void updateDate(String chargeBillId)
;

public List<ChargeBill> queryByOwner(Pager pager)
;

public int countByBlurred(ChargeBill chargeBill,User user)
;

public List<ChargeBill> blurredQuery(Pager pager,ChargeBill chargeBill)
;

public int countByOwner(User user)
;

public List<ChargeBill> queryByCondition(String start,String end,String companyId,String maintainOrFix,String type)
;

}