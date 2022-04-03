package cn.gson.oasys.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class PositionDaoController {

 private PositionDao positiondao;


@GetMapping
("/findAll")
public Object findAll(@RequestParam(name = "Object") Object Object){
  return positiondao.findAll(Object);
}


@GetMapping
("/findOne")
public Object findOne(@RequestParam(name = "Object") Object Object){
  return positiondao.findOne(Object);
}


@GetMapping
("/findByDeptidAndNameNotLike")
public List<Position> findByDeptidAndNameNotLike(@RequestParam(name = "deptid") Long deptid,@RequestParam(name = "name") String name){
  return positiondao.findByDeptidAndNameNotLike(deptid,name);
}


@GetMapping
("/findById")
public String findById(@RequestParam(name = "id") Long id){
  return positiondao.findById(id);
}


}