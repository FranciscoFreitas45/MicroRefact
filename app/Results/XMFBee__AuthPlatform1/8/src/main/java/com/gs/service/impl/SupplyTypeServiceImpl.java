package com.gs.service.impl;
 import com.gs.bean.SupplyType;
import com.gs.bean.User;
import com.gs.common.bean.Pager;
import com.gs.dao.SupplyTypeDAO;
import com.gs.service.SupplyTypeService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
@Service
public class SupplyTypeServiceImpl implements SupplyTypeService{

@Resource
 private  SupplyTypeDAO supplyTypeDAO;


public List<SupplyType> queryByPagerDisable(Pager pager){
    return supplyTypeDAO.queryByPagerDisable(pager);
}


public int batchInsert(List<SupplyType> list){
    return supplyTypeDAO.batchInsert(list);
}


public int batchUpdate(List<SupplyType> list){
    return supplyTypeDAO.batchUpdate(list);
}


public SupplyType query(SupplyType supplyType){
    return supplyTypeDAO.query(supplyType);
}


@Override
public int count(User user){
    return supplyTypeDAO.count(user);
}


public int insert(SupplyType supplyType){
    return supplyTypeDAO.insert(supplyType);
}


public int update(SupplyType supplyType){
    return supplyTypeDAO.update(supplyType);
}


public int active(String id){
    return supplyTypeDAO.active(id);
}


@Override
public List<SupplyType> queryAll(String status){
    return supplyTypeDAO.queryAll(status);
}


public List<SupplyType> blurredQuery(Pager pager,SupplyType supplyType){
    return null;
}


public int delete(SupplyType supplyType){
    return supplyTypeDAO.delete(supplyType);
}


@Override
public int queryNameByOne(String supplyTypeName,String supplyTypeId){
    return supplyTypeDAO.queryNameByOne(supplyTypeName, supplyTypeId);
}


public int batchDelete(List<SupplyType> list){
    return supplyTypeDAO.batchDelete(list);
}


public List<SupplyType> queryByStatus(String status){
    return supplyTypeDAO.queryAll(status);
}


public int inactive(String id){
    return supplyTypeDAO.inactive(id);
}


public int countByBlurred(SupplyType supplyType){
    return 0;
}


@Override
public int countByDisable(){
    return supplyTypeDAO.countByDisable();
}


public int deleteById(String id){
    return supplyTypeDAO.deleteById(id);
}


public SupplyType queryById(String id){
    return supplyTypeDAO.queryById(id);
}


public List<SupplyType> queryByPager(Pager pager){
    return supplyTypeDAO.queryByPager(pager);
}


}