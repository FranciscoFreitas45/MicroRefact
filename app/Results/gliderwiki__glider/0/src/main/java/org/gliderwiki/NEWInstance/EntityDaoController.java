package org.gliderwiki.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class EntityDaoController {

 private EntityDao entitydao;


@GetMapping
("/getRowEntity")
public T getRowEntity(@RequestParam(name = "domain") T domain){
  return entitydao.getRowEntity(domain);
}


@GetMapping
("/updateEntity")
public int updateEntity(@RequestParam(name = "domain") T domain){
  return entitydao.updateEntity(domain);
}


}