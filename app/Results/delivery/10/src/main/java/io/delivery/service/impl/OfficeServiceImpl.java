package io.delivery.service.impl;
 import io.delivery.dao.OfficeDao;
import io.delivery.entity.Office;
import io.delivery.service.OfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service("officeService")
public class OfficeServiceImpl implements OfficeService{

@Autowired
 private  OfficeDao officeDao;


@Override
public List<Office> getOfficeList(){
    return officeDao.getList();
}


public Office findById(Long id){
    return officeDao.findById(id);
}


@Override
public Office create(Office office){
    return officeDao.create(office);
}


@Override
public Office delete(Long id){
    return officeDao.delete(findById(id));
}


}