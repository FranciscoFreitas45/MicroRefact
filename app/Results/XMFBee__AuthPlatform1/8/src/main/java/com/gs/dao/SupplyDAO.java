package com.gs.dao;
 import com.gs.bean.Checkin;
import com.gs.bean.Supply;
import com.gs.bean.User;
import com.gs.common.bean.Pager;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface SupplyDAO extends BaseDAO<String, Supply>{


public List<Supply> queryByPagerDisable(Pager pager)
;

public int countByBlurred(Supply supply,User user)
;

public int countByDisable()
;

public List<Supply> blurredQuery(Pager pager,Supply supply)
;

public int queryNameByOne(String supplyName,String supplyId)
;

}