package com.gs.service.impl;
 import com.gs.bean.AccessoriesBuy;
import com.gs.bean.User;
import com.gs.common.bean.Pager;
import com.gs.dao.AccessoriesBuyDAO;
import com.gs.service.AccessoriesBuyService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
@Service
public class AccessoriesBuyServiceImpl implements AccessoriesBuyService{

@Resource
 private  AccessoriesBuyDAO accessoriesBuyDAO;


public List<AccessoriesBuy> queryByPagerDisable(Pager pager){
    return accessoriesBuyDAO.queryByPagerDisable(pager);
}


public int batchInsert(List<AccessoriesBuy> list){
    return accessoriesBuyDAO.batchInsert(list);
}


public int batchUpdate(List<AccessoriesBuy> list){
    return accessoriesBuyDAO.batchUpdate(list);
}


public AccessoriesBuy query(AccessoriesBuy accessoriesBuy){
    return accessoriesBuyDAO.query(accessoriesBuy);
}


@Override
public int count(User user){
    return accessoriesBuyDAO.count(user);
}


public int insert(AccessoriesBuy accessoriesBuy){
    return accessoriesBuyDAO.insert(accessoriesBuy);
}


public int update(AccessoriesBuy accessoriesBuy){
    return accessoriesBuyDAO.update(accessoriesBuy);
}


public int active(String id){
    return accessoriesBuyDAO.active(id);
}


@Override
public List<AccessoriesBuy> queryAll(String status){
    return accessoriesBuyDAO.queryAll(status);
}


public List<AccessoriesBuy> blurredQuery(Pager pager,AccessoriesBuy accessoriesBuy){
    return accessoriesBuyDAO.blurredQuery(pager, accessoriesBuy);
}


public int delete(AccessoriesBuy accessoriesBuy){
    return accessoriesBuyDAO.delete(accessoriesBuy);
}


public int batchDelete(List<AccessoriesBuy> list){
    return accessoriesBuyDAO.batchDelete(list);
}


public List<AccessoriesBuy> queryByStatus(String status){
    return accessoriesBuyDAO.queryAll(status);
}


public int inactive(String id){
    return accessoriesBuyDAO.inactive(id);
}


@Override
public int countByBlurred(AccessoriesBuy accessoriesBuy,User user){
    return accessoriesBuyDAO.countByBlurred(accessoriesBuy, user);
}


public int deleteById(String id){
    return accessoriesBuyDAO.deleteById(id);
}


public int countByDisable(){
    return accessoriesBuyDAO.countByDisable();
}


@Override
public List<AccessoriesBuy> queryByPayCondition(String start,String end,String companyId,String type){
    return accessoriesBuyDAO.queryByPayCondition(start, end, companyId, type);
}


public AccessoriesBuy queryById(String id){
    return accessoriesBuyDAO.queryById(id);
}


public List<AccessoriesBuy> queryByPager(Pager pager){
    return accessoriesBuyDAO.queryByPager(pager);
}


@Override
public List<AccessoriesBuy> queryByCondition(String start,String end,String companyId,String type){
    return accessoriesBuyDAO.queryByCondition(start, end, companyId, type);
}


}