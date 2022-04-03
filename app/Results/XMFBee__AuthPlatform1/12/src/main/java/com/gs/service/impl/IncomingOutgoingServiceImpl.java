package com.gs.service.impl;
 import com.gs.bean.IncomingOutgoing;
import com.gs.bean.IncomingType;
import com.gs.bean.OutgoingType;
import com.gs.bean.User;
import com.gs.common.bean.Pager;
import com.gs.dao.IncomingOutgoingDAO;
import com.gs.service.IncomingOutgoingService;
import org.activiti.engine.impl.Page;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
@Service
public class IncomingOutgoingServiceImpl implements IncomingOutgoingService{

@Resource
 private  IncomingOutgoingDAO incomingOutgoingDAO;


public List<IncomingOutgoing> queryByPagerDisable(Pager pager){
    return incomingOutgoingDAO.queryByPagerDisable(pager);
}


public int batchInsert(List<IncomingOutgoing> list){
    return incomingOutgoingDAO.batchInsert(list);
}


public int batchUpdate(List<IncomingOutgoing> list){
    return incomingOutgoingDAO.batchUpdate(list);
}


public IncomingOutgoing query(IncomingOutgoing incomingOutgoing){
    return incomingOutgoingDAO.query(incomingOutgoing);
}


public int count(User user){
    return incomingOutgoingDAO.count(user);
}


public int insert(IncomingOutgoing incomingOutgoing){
    return incomingOutgoingDAO.insert(incomingOutgoing);
}


public int update(IncomingOutgoing incomingOutgoing){
    return incomingOutgoingDAO.update(incomingOutgoing);
}


public int active(String id){
    return incomingOutgoingDAO.active(id);
}


public List<IncomingOutgoing> queryByDate(String inOutType,String companyId){
    return incomingOutgoingDAO.queryByDate(inOutType, companyId);
}


public List<IncomingOutgoing> queryAll(User user,String status){
    return incomingOutgoingDAO.queryAll(user);
}


public List<IncomingOutgoing> blurredQuery(Pager pager,IncomingOutgoing incomingOutgoing){
    return incomingOutgoingDAO.blurredQuery(pager, incomingOutgoing);
}


public int delete(IncomingOutgoing incomingOutgoing){
    return incomingOutgoingDAO.delete(incomingOutgoing);
}


@Override
public List<IncomingOutgoing> queryByCompanyIdForOutType(String companyId){
    return incomingOutgoingDAO.queryByCompanyIdForOutType(companyId);
}


public int batchDelete(List<IncomingOutgoing> list){
    return incomingOutgoingDAO.batchDelete(list);
}


@Override
public List<IncomingOutgoing> queryByCompanyIdForInType(String companyId){
    return incomingOutgoingDAO.queryByCompanyIdForInType(companyId);
}


public List<IncomingOutgoing> queryByStatus(String status){
    return incomingOutgoingDAO.queryAll(status);
}


@Override
public void addInsert(List<IncomingOutgoing> incomingOutgoings){
    incomingOutgoingDAO.addInsert(incomingOutgoings);
}


public int inactive(String id){
    return incomingOutgoingDAO.inactive(id);
}


public int countByBlurred(IncomingOutgoing incomingOutgoing){
    return 0;
}


public int deleteById(String id){
    return incomingOutgoingDAO.deleteById(id);
}


public int countByDisable(){
    return incomingOutgoingDAO.countByDisable();
}


public IncomingOutgoing queryById(String id){
    return incomingOutgoingDAO.queryById(id);
}


public List<IncomingOutgoing> queryByPager(Pager pager){
    return incomingOutgoingDAO.queryByPager(pager);
}


@Override
public List<IncomingOutgoing> queryByCondition(String start,String end,String inOutType,String companyId,String type){
    return incomingOutgoingDAO.queryByCondition(start, end, inOutType, companyId, type);
}


}