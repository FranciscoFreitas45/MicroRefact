package cn.maxcj.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class DeptMapperController {

 private DeptMapper deptmapper;


@GetMapping
("/selectById")
public Object selectById(@RequestParam(name = "Object") Object Object){
  return deptmapper.selectById(Object);
}


@GetMapping
("/selectList")
public Object selectList(@RequestParam(name = "Object") Object Object){
  return deptmapper.selectList(Object);
}


}