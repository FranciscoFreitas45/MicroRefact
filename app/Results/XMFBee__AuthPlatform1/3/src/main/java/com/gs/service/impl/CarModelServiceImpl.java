package com.gs.service.impl;
 import com.gs.bean.CarModel;
import com.gs.bean.Checkin;
import com.gs.bean.User;
import com.gs.dao.CarModelDAO;
import com.gs.service.CarModelService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.gs.common.bean.Pager;
@Service
public class CarModelServiceImpl implements CarModelService{

@Resource
 private  CarModelDAO carModelDAO;


public List<CarModel> queryByPagerDisable(Pager pager){
    return carModelDAO.queryByPagerDisable(pager);
}


public int batchInsert(List<CarModel> list){
    return carModelDAO.batchInsert(list);
}


public int batchUpdate(List<CarModel> list){
    return carModelDAO.batchUpdate(list);
}


public CarModel query(CarModel carModel){
    return carModelDAO.query(carModel);
}


public int count(User user){
    return carModelDAO.count(user);
}


public int insert(CarModel carModel){
    return carModelDAO.insert(carModel);
}


public int update(CarModel carModel){
    return carModelDAO.update(carModel);
}


public int active(String id){
    return carModelDAO.active(id);
}


@Override
public List<CarModel> queryAll(String status){
    return carModelDAO.queryAll(status);
}


public List<CarModel> blurredQuery(Pager pager,CarModel carModel){
    return carModelDAO.blurredQuery(pager, carModel);
}


public int delete(CarModel carModel){
    return carModelDAO.delete(carModel);
}


public List<CarModel> queryByBrandId(String id){
    return carModelDAO.queryByBrandId(id);
}


public int batchDelete(List<CarModel> list){
    return carModelDAO.batchDelete(list);
}


public List<CarModel> queryByStatus(String status){
    return carModelDAO.queryAll(status);
}


public int inactive(String id){
    return carModelDAO.inactive(id);
}


public int countByBlurred(CarModel carModel,User user){
    return carModelDAO.countByBlurred(carModel, user);
}


public int deleteById(String id){
    return carModelDAO.deleteById(id);
}


@Override
public int countByDisable(User user){
    return carModelDAO.countByDisable(user);
}


@Override
public int querymodelName(String modelName,String modelId){
    return carModelDAO.querymodelName(modelName, modelId);
}


public CarModel queryById(String id){
    return carModelDAO.queryById(id);
}


public List<CarModel> queryByPager(Pager pager){
    return carModelDAO.queryByPager(pager);
}


}