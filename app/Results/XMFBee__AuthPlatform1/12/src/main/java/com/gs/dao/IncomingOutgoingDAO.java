package com.gs.dao;
 import com.gs.bean;
import com.gs.common.bean.Pager;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface IncomingOutgoingDAO extends BaseDAO<String, IncomingOutgoing>{


public List<IncomingOutgoing> queryByCompanyIdForInType(String companyId)
;

public void addInsert(List<IncomingOutgoing> incomingOutgoings)
;

public List<IncomingOutgoing> queryByPagerDisable(Pager pager)
;

public int countByBlurred(User user,IncomingOutgoing incomingOutgoing)
;

public int countByDisable()
;

public List<IncomingOutgoing> queryByDate(String inOutType,String companyId)
;

public List<IncomingOutgoing> blurredQuery(Pager pager,IncomingOutgoing incomingOutgoing)
;

public List<IncomingOutgoing> queryByCompanyIdForOutType(String companyId)
;

public List<IncomingOutgoing> queryByCondition(String start,String end,String inOutType,String companyId,String type)
;

}