package com.gs.service.impl;
 import com.gs.bean.ChargeBill;
import com.gs.bean.User;
import com.gs.common.bean.Pager;
import com.gs.dao.ChargeBillDAO;
import com.gs.service.ChargeBillService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
@Service
public class ChargeBillServiceImpl implements ChargeBillService{

@Resource
 private  ChargeBillDAO chargeBillDAO;


@Override
public void updateDate(String chargeBillId){
    chargeBillDAO.updateDate(chargeBillId);
}


@Override
public List<ChargeBill> queryByOwner(Pager pager){
    return chargeBillDAO.queryByOwner(pager);
}


public List<ChargeBill> queryByPagerDisable(Pager pager){
    return chargeBillDAO.queryByPagerDisable(pager);
}


public int batchInsert(List<ChargeBill> list){
    return chargeBillDAO.batchInsert(list);
}


public int batchUpdate(List<ChargeBill> list){
    return chargeBillDAO.batchUpdate(list);
}


public ChargeBill query(ChargeBill chargeBill){
    return chargeBillDAO.query(chargeBill);
}


public int count(User user){
    return chargeBillDAO.count(user);
}


public int insert(ChargeBill chargeBill){
    return chargeBillDAO.insert(chargeBill);
}


public int update(ChargeBill chargeBill){
    return chargeBillDAO.update(chargeBill);
}


public int active(String id){
    return chargeBillDAO.active(id);
}


public List<ChargeBill> queryAll(String status){
    return chargeBillDAO.queryAll(status);
}


public List<ChargeBill> blurredQuery(Pager pager,ChargeBill chargeBill){
    return chargeBillDAO.blurredQuery(pager, chargeBill);
}


public int delete(ChargeBill chargeBill){
    return chargeBillDAO.delete(chargeBill);
}


@Override
public int countByOwner(User frontUser){
    return chargeBillDAO.countByOwner(frontUser);
}


public int batchDelete(List<ChargeBill> list){
    return chargeBillDAO.batchDelete(list);
}


public List<ChargeBill> queryByStatus(String status){
    return chargeBillDAO.queryAll(status);
}


@Override
public void updateCurrent(String id){
    chargeBillDAO.updateCurrent(id);
}


public int inactive(String id){
    return chargeBillDAO.inactive(id);
}


public int countByBlurred(ChargeBill chargeBill,User user){
    return chargeBillDAO.countByBlurred(chargeBill, user);
}


public int deleteById(String id){
    return chargeBillDAO.deleteById(id);
}


public int countByDisable(User user){
    return chargeBillDAO.countByDisable(user);
}


public ChargeBill queryById(String id){
    return chargeBillDAO.queryById(id);
}


public List<ChargeBill> queryByPager(Pager pager){
    return chargeBillDAO.queryByPager(pager);
}


@Override
public List<ChargeBill> queryByCondition(String start,String end,String userId,String maintainOrFix,String type){
    return chargeBillDAO.queryByCondition(start, end, userId, maintainOrFix, type);
}


}