package com.gs.service.impl;
 import com.gs.bean.AccessoriesBuy;
import com.gs.bean.AccessoriesSale;
import com.gs.bean.Checkin;
import com.gs.bean.User;
import com.gs.common.bean.Pager;
import com.gs.dao.AccessoriesSaleDAO;
import com.gs.service.AccessoriesSaleService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
@Service
public class AccessoriesSaleServiceImpl implements AccessoriesSaleService{

@Resource
 private  AccessoriesSaleDAO accessoriesSaleDAO;


public List<AccessoriesSale> queryByPagerDisable(Pager pager){
    return accessoriesSaleDAO.queryByPagerDisable(pager);
}


public int batchInsert(List<AccessoriesSale> list){
    return accessoriesSaleDAO.batchInsert(list);
}


public int batchUpdate(List<AccessoriesSale> list){
    return accessoriesSaleDAO.batchUpdate(list);
}


public AccessoriesSale query(AccessoriesSale accessoriesSale){
    return accessoriesSaleDAO.query(accessoriesSale);
}


@Override
public int count(User user){
    return accessoriesSaleDAO.count(user);
}


public int insert(AccessoriesSale accessoriesSale){
    return accessoriesSaleDAO.insert(accessoriesSale);
}


public int update(AccessoriesSale accessoriesSale){
    return accessoriesSaleDAO.update(accessoriesSale);
}


public int active(String id){
    return accessoriesSaleDAO.active(id);
}


@Override
public List<AccessoriesSale> queryAll(String status){
    return accessoriesSaleDAO.queryAll(status);
}


public List<AccessoriesSale> blurredQuery(Pager pager,AccessoriesSale accessoriesSale){
    return accessoriesSaleDAO.blurredQuery(pager, accessoriesSale);
}


public int delete(AccessoriesSale accessoriesSale){
    return accessoriesSaleDAO.delete(accessoriesSale);
}


public int batchDelete(List<AccessoriesSale> list){
    return accessoriesSaleDAO.batchDelete(list);
}


public List<AccessoriesSale> queryByStatus(String status){
    return accessoriesSaleDAO.queryAll(status);
}


public int inactive(String id){
    return accessoriesSaleDAO.inactive(id);
}


@Override
public int countByBlurred(AccessoriesSale accessoriesSale,User user){
    return accessoriesSaleDAO.countByBlurred(accessoriesSale, user);
}


public int deleteById(String id){
    return accessoriesSaleDAO.deleteById(id);
}


public int countByDisable(){
    return accessoriesSaleDAO.countByDisable();
}


public AccessoriesSale queryById(String id){
    return accessoriesSaleDAO.queryById(id);
}


public List<AccessoriesSale> queryByPager(Pager pager){
    return accessoriesSaleDAO.queryByPager(pager);
}


}