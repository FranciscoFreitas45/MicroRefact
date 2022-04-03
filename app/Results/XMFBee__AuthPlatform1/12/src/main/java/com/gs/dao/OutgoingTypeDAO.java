package com.gs.dao;
 import com.gs.bean.Checkin;
import com.gs.bean.IncomingType;
import com.gs.bean.OutgoingType;
import com.gs.common.bean.Pager;
import com.gs.common.bean.Pager4EasyUI;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface OutgoingTypeDAO extends BaseDAO<String, OutgoingType>{


public List<OutgoingType> queryByPagerDisable(Pager pager)
;

public int countByDisable()
;

public OutgoingType queryById(String outTypeName,String outTypeId)
;

}