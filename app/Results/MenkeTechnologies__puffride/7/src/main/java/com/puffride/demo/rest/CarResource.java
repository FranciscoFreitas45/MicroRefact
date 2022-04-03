package com.puffride.demo.rest;
 import org.springframework.web.bind.annotation;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import com.puffride.demo.entity.Car;
import com.puffride.demo.dao.CarDao;
import com.puffride.demo.utils.GlobalConstants;
@RestController
@RequestMapping(GlobalConstants.CONTEXT_PATH + "/car")
public class CarResource {

@Autowired
 private CarDao dao;


@GetMapping("/{id}")
public Car read(Long id){
    return dao.findOne(id);
}


@DeleteMapping
public boolean deleteAll(List<Car> entityList){
    dao.deleteAll(entityList);
    return true;
}


@PostMapping
public Car create(Car entity){
    return dao.save(entity);
}


@PutMapping
public Car update(Car entity){
    return dao.save(entity);
}


@DeleteMapping("/{id}")
public boolean delete(Long id){
    dao.delete(id);
    return true;
}


@GetMapping
public List<Car> readAll(){
    return dao.findAll();
}


}