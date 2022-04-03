package com.puffride.demo.rest;
 import org.springframework.web.bind.annotation;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import com.puffride.demo.entity.Rating;
import com.puffride.demo.dao.RatingDao;
import com.puffride.demo.utils.GlobalConstants;
@RestController
@RequestMapping(GlobalConstants.CONTEXT_PATH + "/rating")
public class RatingResource {

@Autowired
 private RatingDao dao;


@GetMapping("/{id}")
public Rating read(Long id){
    return dao.findOne(id);
}


@DeleteMapping
public boolean deleteAll(List<Rating> entityList){
    dao.deleteAll(entityList);
    return true;
}


@PostMapping
public Rating create(Rating entity){
    return dao.save(entity);
}


@PutMapping
public Rating update(Rating entity){
    return dao.save(entity);
}


@DeleteMapping("/{id}")
public boolean delete(Long id){
    dao.delete(id);
    return true;
}


@GetMapping
public List<Rating> readAll(){
    return dao.findAll();
}


}