package com.empl.mgr.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class PositionDaoController {

 private PositionDao positiondao;


@GetMapping
("/findUniqueByProperty")
public Object findUniqueByProperty(@RequestParam(name = "Object") Object Object){
  return positiondao.findUniqueByProperty(Object);
}


@GetMapping
("/findByDeptId")
public List<PositionDto> findByDeptId(@RequestParam(name = "deptId") long deptId){
  return positiondao.findByDeptId(deptId);
}


}