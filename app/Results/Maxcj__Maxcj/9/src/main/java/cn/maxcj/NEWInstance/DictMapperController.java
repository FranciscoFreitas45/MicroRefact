package cn.maxcj.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class DictMapperController {

 private DictMapper dictmapper;


@GetMapping
("/selectById")
public Object selectById(@RequestParam(name = "Object") Object Object){
  return dictmapper.selectById(Object);
}


@GetMapping
("/selectOne")
public Object selectOne(@RequestParam(name = "Object") Object Object){
  return dictmapper.selectOne(Object);
}


@GetMapping
("/selectList")
public Object selectList(@RequestParam(name = "Object") Object Object){
  return dictmapper.selectList(Object);
}


}