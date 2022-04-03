package com.puffride.demo.rest;
 import org.springframework.web.bind.annotation;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import com.puffride.demo.entity.Ride;
import com.puffride.demo.dao.RideDao;
import com.puffride.demo.utils.GlobalConstants;
@RestController
@RequestMapping(GlobalConstants.CONTEXT_PATH + "/ride")
public class RideResource {

@Autowired
 private RideDao dao;


@GetMapping("/{id}")
public Ride read(Long id){
    return dao.findOne(id);
}


@DeleteMapping
public boolean deleteAll(List<Ride> entityList){
    dao.deleteAll(entityList);
    return true;
}


@PostMapping
public Ride create(Ride entity){
    return dao.save(entity);
}


@PutMapping
public Ride update(Ride entity){
    return dao.save(entity);
}


@DeleteMapping("/{id}")
public boolean delete(Long id){
    dao.delete(id);
    return true;
}


@GetMapping
public List<Ride> readAll(){
    return dao.findAll();
}


}