package com.gs.service.impl;
 import com.gs.bean.Checkin;
import com.gs.bean.IncomingType;
import com.gs.bean.OutgoingType;
import com.gs.bean.User;
import com.gs.dao.IncomingTypeDAO;
import com.gs.service.IncomingTypeService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.gs.common.bean.Pager;
@Service
public class IncomingTypeServiceImpl implements IncomingTypeService{

@Resource
 private  IncomingTypeDAO incomingTypeDAO;


@Override
public List<IncomingType> queryByPagerDisable(Pager pager){
    return incomingTypeDAO.queryByPagerDisable(pager);
}


public int batchInsert(List<IncomingType> list){
    return incomingTypeDAO.batchInsert(list);
}


public int batchUpdate(List<IncomingType> list){
    return incomingTypeDAO.batchUpdate(list);
}


public IncomingType query(IncomingType incomingType){
    return incomingTypeDAO.query(incomingType);
}


public int count(User user){
    return incomingTypeDAO.count(user);
}


public int insert(IncomingType incomingType){
    return incomingTypeDAO.insert(incomingType);
}


public int update(IncomingType incomingType){
    return incomingTypeDAO.update(incomingType);
}


public int active(String id){
    return incomingTypeDAO.active(id);
}


@Override
public List<IncomingType> queryAll(String status){
    return incomingTypeDAO.queryAll(status);
}


public List<IncomingType> blurredQuery(Pager pager,IncomingType incomingType){
    return null;
}


public int delete(IncomingType incomingType){
    return incomingTypeDAO.delete(incomingType);
}


public int batchDelete(List<IncomingType> list){
    return incomingTypeDAO.batchDelete(list);
}


public List<IncomingType> queryByStatus(String status){
    return incomingTypeDAO.queryAll(status);
}


public int inactive(String id){
    return incomingTypeDAO.inactive(id);
}


public int countByBlurred(IncomingType incomingType){
    return 0;
}


public int deleteById(String id){
    return incomingTypeDAO.deleteById(id);
}


@Override
public int countByDisable(){
    return incomingTypeDAO.countByDisable();
}


@Override
public IncomingType queryById(String inTypeName,String inTypeId){
    return incomingTypeDAO.queryById(inTypeName, inTypeId);
}


public List<IncomingType> queryByPager(Pager pager){
    return incomingTypeDAO.queryByPager(pager);
}


}