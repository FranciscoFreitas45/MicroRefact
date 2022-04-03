package com.puffride.demo.rest;
 import org.springframework.web.bind.annotation;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import com.puffride.demo.entity.Driver;
import com.puffride.demo.dao.DriverDao;
import com.puffride.demo.utils.GlobalConstants;
@RestController
@RequestMapping(GlobalConstants.CONTEXT_PATH + "/driver")
public class DriverResource {

@Autowired
 private DriverDao dao;


@GetMapping("/{id}")
public Driver read(Long id){
    return dao.findOne(id);
}


@DeleteMapping
public boolean deleteAll(List<Driver> entityList){
    dao.deleteAll(entityList);
    return true;
}


@PostMapping
public Driver create(Driver entity){
    return dao.save(entity);
}


@PutMapping
public Driver update(Driver entity){
    return dao.save(entity);
}


@DeleteMapping("/{id}")
public boolean delete(Long id){
    dao.delete(id);
    return true;
}


@GetMapping
public List<Driver> readAll(){
    return dao.findAll();
}


}