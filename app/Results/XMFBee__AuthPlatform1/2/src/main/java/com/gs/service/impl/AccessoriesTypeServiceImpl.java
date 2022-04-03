package com.gs.service.impl;
 import com.gs.bean.AccessoriesType;
import com.gs.bean.Checkin;
import com.gs.bean.User;
import com.gs.dao.AccessoriesTypeDAO;
import com.gs.service.AccessoriesTypeService;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.gs.common.bean.Pager;
@Service
public class AccessoriesTypeServiceImpl implements AccessoriesTypeService{

@Resource
 private  AccessoriesTypeDAO accessoriesTypeDAO;


@Override
public List<AccessoriesType> queryByPagerDisable(Pager pager){
    return accessoriesTypeDAO.queryByPagerDisable(pager);
}


public int batchInsert(List<AccessoriesType> list){
    return accessoriesTypeDAO.batchInsert(list);
}


public int batchUpdate(List<AccessoriesType> list){
    return accessoriesTypeDAO.batchUpdate(list);
}


public AccessoriesType query(AccessoriesType accessoriesType){
    return accessoriesTypeDAO.query(accessoriesType);
}


@Override
public int count(User user){
    return accessoriesTypeDAO.count(user);
}


public int insert(AccessoriesType accessoriesType){
    return accessoriesTypeDAO.insert(accessoriesType);
}


public int update(AccessoriesType accessoriesType){
    return accessoriesTypeDAO.update(accessoriesType);
}


public int active(String id){
    return accessoriesTypeDAO.active(id);
}


@Override
public int queryAccTypeNameOne(String accTypeName,String accTypeId){
    return accessoriesTypeDAO.queryAccTypeNameOne(accTypeName, accTypeId);
}


@Override
public List<AccessoriesType> queryAll(String status){
    return accessoriesTypeDAO.queryAll(status);
}


public List<AccessoriesType> blurredQuery(Pager pager,AccessoriesType accessoriesType){
    return accessoriesTypeDAO.blurredQuery(pager, accessoriesType);
}


public int delete(AccessoriesType accessoriesType){
    return accessoriesTypeDAO.delete(accessoriesType);
}


public int batchDelete(List<AccessoriesType> list){
    return accessoriesTypeDAO.batchDelete(list);
}


public int inactive(String id){
    return accessoriesTypeDAO.inactive(id);
}


@Override
public int countByBlurred(AccessoriesType accessoriesType,User user){
    return accessoriesTypeDAO.countByBlurred(accessoriesType, user);
}


public int deleteById(String id){
    return accessoriesTypeDAO.deleteById(id);
}


@Override
public int countByDisable(){
    return accessoriesTypeDAO.countByDisable();
}


@Override
public List<AccessoriesType> queryTypeName(String companyId){
    return accessoriesTypeDAO.queryTypeName(companyId);
}


public AccessoriesType queryById(String id){
    return accessoriesTypeDAO.queryById(id);
}


public List<AccessoriesType> queryByPager(Pager pager){
    return accessoriesTypeDAO.queryByPager(pager);
}


}