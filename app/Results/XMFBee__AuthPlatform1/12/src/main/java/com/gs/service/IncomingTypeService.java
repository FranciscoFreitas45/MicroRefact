package com.gs.service;
 import com.gs.bean.Checkin;
import com.gs.bean.IncomingType;
import com.gs.bean.OutgoingType;
import com.gs.common.bean.Pager;
import java.util.List;
public interface IncomingTypeService extends BaseService<String, IncomingType>{


public List<IncomingType> queryByPagerDisable(Pager pager)
;

public int countByDisable()
;

public IncomingType queryById(String inTypeName,String inTypeId)
;

}