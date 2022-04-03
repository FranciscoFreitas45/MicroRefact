package com.gs.service.impl;
 import com.gs.bean.CarPlate;
import com.gs.bean.Checkin;
import com.gs.bean.User;
import com.gs.dao.CarPlateDAO;
import com.gs.service.CarPlateService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.gs.common.bean.Pager;
import org.apache.shiro.web.filter.mgt.DefaultFilter.user;
@Service
public class CarPlateServiceImpl implements CarPlateService{

@Resource
 private  CarPlateDAO carPlateDAO;


public List<CarPlate> queryByPagerDisable(Pager pager){
    return carPlateDAO.queryByPagerDisable(pager);
}


public int batchInsert(List<CarPlate> list){
    return carPlateDAO.batchInsert(list);
}


public int batchUpdate(List<CarPlate> list){
    return carPlateDAO.batchUpdate(list);
}


public CarPlate query(CarPlate carPlate){
    return carPlateDAO.query(carPlate);
}


@Override
public int queryplateName(String plateName,String plateId){
    return carPlateDAO.queryplateName(plateName, plateId);
}


public int count(User user){
    return carPlateDAO.count(user);
}


public int insert(CarPlate carPlate){
    return carPlateDAO.insert(carPlate);
}


public int update(CarPlate carPlate){
    return carPlateDAO.update(carPlate);
}


public int active(String id){
    return carPlateDAO.active(id);
}


@Override
public List<CarPlate> queryAll(String status){
    return carPlateDAO.queryAll(status);
}


public List<CarPlate> blurredQuery(Pager pager,CarPlate carPlate){
    return carPlateDAO.blurredQuery(pager, carPlate);
}


public int delete(CarPlate carPlate){
    return carPlateDAO.delete(carPlate);
}


public int batchDelete(List<CarPlate> list){
    return carPlateDAO.batchDelete(list);
}


public List<CarPlate> queryByStatus(String status){
    return carPlateDAO.queryAll(status);
}


public int inactive(String id){
    return carPlateDAO.inactive(id);
}


@Override
public int countByBlurred(CarPlate carPlate,User user){
    return carPlateDAO.countByBlurred(carPlate, user);
}


public int deleteById(String id){
    return carPlateDAO.deleteById(id);
}


@Override
public int countByDisable(User user){
    return carPlateDAO.countByDisable(user);
}


public CarPlate queryById(String id){
    return carPlateDAO.queryById(id);
}


public List<CarPlate> queryByPager(Pager pager){
    return carPlateDAO.queryByPager(pager);
}


}