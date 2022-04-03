package com.puffride.demo.rest;
 import org.springframework.web.bind.annotation;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import com.puffride.demo.entity.Phone;
import com.puffride.demo.dao.PhoneDao;
import com.puffride.demo.utils.GlobalConstants;
@RestController
@RequestMapping(GlobalConstants.CONTEXT_PATH + "/phone")
public class PhoneResource {

@Autowired
 private PhoneDao dao;


@GetMapping("/{id}")
public Phone read(Long id){
    return dao.findOne(id);
}


@DeleteMapping
public boolean deleteAll(List<Phone> entityList){
    dao.deleteAll(entityList);
    return true;
}


@PostMapping
public Phone create(Phone entity){
    return dao.save(entity);
}


@PutMapping
public Phone update(Phone entity){
    return dao.save(entity);
}


@DeleteMapping("/{id}")
public boolean delete(Long id){
    dao.delete(id);
    return true;
}


@GetMapping
public List<Phone> readAll(){
    return dao.findAll();
}


}