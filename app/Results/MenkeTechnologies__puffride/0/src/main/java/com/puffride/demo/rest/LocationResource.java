package com.puffride.demo.rest;
 import org.springframework.web.bind.annotation;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import com.puffride.demo.entity.Location;
import com.puffride.demo.dao.LocationDao;
import com.puffride.demo.utils.GlobalConstants;
@RestController
@RequestMapping(GlobalConstants.CONTEXT_PATH + "/location")
public class LocationResource {

@Autowired
 private LocationDao dao;


@GetMapping("/{id}")
public Location read(Long id){
    return dao.findOne(id);
}


@DeleteMapping
public boolean deleteAll(List<Location> entityList){
    dao.deleteAll(entityList);
    return true;
}


@PostMapping
public Location create(Location entity){
    return dao.save(entity);
}


@PutMapping
public Location update(Location entity){
    return dao.save(entity);
}


@DeleteMapping("/{id}")
public boolean delete(Long id){
    dao.delete(id);
    return true;
}


@GetMapping
public List<Location> readAll(){
    return dao.findAll();
}


}