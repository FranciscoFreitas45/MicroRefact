package com.gs.dao;
 import com.gs.bean.Checkin;
import com.gs.bean.IncomingType;
import com.gs.bean.OutgoingType;
import com.gs.common.bean.Pager;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface IncomingTypeDAO extends BaseDAO<String, IncomingType>{


public List<IncomingType> queryByPagerDisable(Pager pager)
;

public int countByDisable()
;

public IncomingType queryById(String inTypeName,String inTypeId)
;

}