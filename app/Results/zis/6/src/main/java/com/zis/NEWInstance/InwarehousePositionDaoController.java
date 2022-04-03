package com.zis.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class InwarehousePositionDaoController {

 private InwarehousePositionDao inwarehousepositiondao;


@GetMapping
("/save")
public Object save(@RequestParam(name = "Object") Object Object){
  return inwarehousepositiondao.save(Object);
}


@GetMapping
("/findAvailablePosition")
public List<InwarehousePosition> findAvailablePosition(@RequestParam(name = "inwarehouseId") Integer inwarehouseId){
  return inwarehousepositiondao.findAvailablePosition(inwarehouseId);
}


}