package com.gs.service;
 import com.gs.bean;
import com.gs.common.bean.Pager;
import org.apache.ibatis.annotations.Param;
import java.util.List;
public interface IncomingOutgoingService extends BaseService<String, IncomingOutgoing>{


public List<IncomingOutgoing> queryByCompanyIdForInType(String companyId)
;

public void addInsert(List<IncomingOutgoing> incomingOutgoings)
;

public List<IncomingOutgoing> queryByPagerDisable(Pager pager)
;

public int countByDisable()
;

public List<IncomingOutgoing> queryByDate(String inOutType,String companyId)
;

public List<IncomingOutgoing> queryByCompanyIdForOutType(String companyId)
;

public List<IncomingOutgoing> queryByCondition(String start,String end,String inOutType,String companyId,String type)
;

}