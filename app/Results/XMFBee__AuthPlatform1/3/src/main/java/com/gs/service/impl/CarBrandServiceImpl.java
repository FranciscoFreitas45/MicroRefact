package com.gs.service.impl;
 import com.gs.bean.CarBrand;
import com.gs.bean.User;
import com.gs.common.bean.Pager;
import com.gs.dao.CarBrandDAO;
import com.gs.service.CarBrandService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
@Service
public class CarBrandServiceImpl implements CarBrandService{

@Resource
 private  CarBrandDAO carBrandDAO;


public List<CarBrand> queryByPagerDisable(Pager pager){
    return carBrandDAO.queryByPagerDisable(pager);
}


public int batchInsert(List<CarBrand> list){
    return carBrandDAO.batchInsert(list);
}


public int batchUpdate(List<CarBrand> list){
    return carBrandDAO.batchUpdate(list);
}


public CarBrand query(CarBrand carBrand){
    return carBrandDAO.query(carBrand);
}


public int count(User user){
    return carBrandDAO.count(user);
}


public int insert(CarBrand carBrand){
    return carBrandDAO.insert(carBrand);
}


public int update(CarBrand carBrand){
    return carBrandDAO.update(carBrand);
}


public int active(String id){
    return carBrandDAO.active(id);
}


public List<CarBrand> queryAll(String status){
    return carBrandDAO.queryAll(status);
}


public List<CarBrand> blurredQuery(Pager pager,CarBrand carBrand){
    return carBrandDAO.blurredQuery(pager, carBrand);
}


public int delete(CarBrand carBrand){
    return carBrandDAO.delete(carBrand);
}


public int batchDelete(List<CarBrand> list){
    return carBrandDAO.batchDelete(list);
}


public List<CarBrand> queryByStatus(String status){
    return carBrandDAO.queryAll(status);
}


public int inactive(String id){
    return carBrandDAO.inactive(id);
}


public int countByBlurred(CarBrand carBrand,User user){
    return carBrandDAO.countByBlurred(carBrand, user);
}


@Override
public int querybrandName(String brandName,String brandId){
    return carBrandDAO.querybrandName(brandName, brandId);
}


public int deleteById(String id){
    return carBrandDAO.deleteById(id);
}


public int countByDisable(User user){
    return carBrandDAO.countByDisable(user);
}


public CarBrand queryById(String id){
    return carBrandDAO.queryById(id);
}


public List<CarBrand> queryByPager(Pager pager){
    return carBrandDAO.queryByPager(pager);
}


}