package com.gs.service.impl;
 import com.gs.bean.OutgoingType;
import com.gs.bean.User;
import com.gs.common.bean.Pager;
import com.gs.dao.OutgoingTypeDAO;
import com.gs.service.OutgoingTypeService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
@Service
public class OutgoingTypeServiceImpl implements OutgoingTypeService{

@Resource
 private  OutgoingTypeDAO outgoingTypeDAO;


@Override
public List<OutgoingType> queryByPagerDisable(Pager pager){
    return outgoingTypeDAO.queryByPagerDisable(pager);
}


public int batchInsert(List<OutgoingType> list){
    return outgoingTypeDAO.batchInsert(list);
}


public int batchUpdate(List<OutgoingType> list){
    return outgoingTypeDAO.batchUpdate(list);
}


public OutgoingType query(OutgoingType outgoingType){
    return outgoingTypeDAO.query(outgoingType);
}


public int count(User user){
    return outgoingTypeDAO.count(user);
}


public int insert(OutgoingType outgoingType){
    return outgoingTypeDAO.insert(outgoingType);
}


public int update(OutgoingType outgoingType){
    return outgoingTypeDAO.update(outgoingType);
}


public int active(String id){
    return outgoingTypeDAO.active(id);
}


@Override
public List<OutgoingType> queryAll(String status){
    return outgoingTypeDAO.queryAll(status);
}


public List<OutgoingType> blurredQuery(Pager pager,OutgoingType outgoingType){
    return null;
}


public int delete(OutgoingType outgoingType){
    return outgoingTypeDAO.delete(outgoingType);
}


public int batchDelete(List<OutgoingType> list){
    return outgoingTypeDAO.batchDelete(list);
}


public List<OutgoingType> queryByStatus(String status){
    return outgoingTypeDAO.queryAll(status);
}


public int inactive(String id){
    return outgoingTypeDAO.inactive(id);
}


public int countByBlurred(OutgoingType outgoingType){
    return 0;
}


public int deleteById(String id){
    return outgoingTypeDAO.deleteById(id);
}


@Override
public int countByDisable(){
    return outgoingTypeDAO.countByDisable();
}


@Override
public OutgoingType queryById(String outTypeName,String outTypeId){
    return outgoingTypeDAO.queryById(outTypeName, outTypeId);
}


public List<OutgoingType> queryByPager(Pager pager){
    return outgoingTypeDAO.queryByPager(pager);
}


}