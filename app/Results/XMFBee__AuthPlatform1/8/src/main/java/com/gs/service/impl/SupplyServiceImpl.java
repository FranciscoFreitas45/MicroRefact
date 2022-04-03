package com.gs.service.impl;
 import com.gs.bean.Supply;
import com.gs.bean.User;
import com.gs.common.bean.Pager;
import com.gs.dao.SupplyDAO;
import com.gs.service.SupplyService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
@Service
public class SupplyServiceImpl implements SupplyService{

@Resource
 private  SupplyDAO supplyDAO;


public List<Supply> queryByPagerDisable(Pager pager){
    return supplyDAO.queryByPagerDisable(pager);
}


public int batchInsert(List<Supply> list){
    return supplyDAO.batchInsert(list);
}


public int batchUpdate(List<Supply> list){
    return supplyDAO.batchUpdate(list);
}


public Supply query(Supply supply){
    return supplyDAO.query(supply);
}


@Override
public int count(User user){
    return supplyDAO.count(user);
}


public int insert(Supply supply){
    return supplyDAO.insert(supply);
}


public int update(Supply supply){
    return supplyDAO.update(supply);
}


public int active(String id){
    return supplyDAO.active(id);
}


@Override
public List<Supply> queryAll(String status){
    return supplyDAO.queryAll(status);
}


public List<Supply> blurredQuery(Pager pager,Supply supply){
    return supplyDAO.blurredQuery(pager, supply);
}


public int delete(Supply supply){
    return supplyDAO.delete(supply);
}


public int batchDelete(List<Supply> list){
    return supplyDAO.batchDelete(list);
}


@Override
public int queryNameByOne(String supplyName,String supplyId){
    return supplyDAO.queryNameByOne(supplyName, supplyId);
}


public List<Supply> queryByStatus(String status){
    return supplyDAO.queryAll(status);
}


public int inactive(String id){
    return supplyDAO.inactive(id);
}


@Override
public int countByBlurred(Supply supply,User user){
    return supplyDAO.countByBlurred(supply, user);
}


public int deleteById(String id){
    return supplyDAO.deleteById(id);
}


@Override
public int countByDisable(User user){
    return supplyDAO.countByDisable();
}


public Supply queryById(String id){
    return supplyDAO.queryById(id);
}


public List<Supply> queryByPager(Pager pager){
    return supplyDAO.queryByPager(pager);
}


}