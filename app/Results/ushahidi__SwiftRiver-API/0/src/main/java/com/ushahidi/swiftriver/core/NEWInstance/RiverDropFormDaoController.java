package com.ushahidi.swiftriver.core.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class RiverDropFormDaoController {

 private RiverDropFormDao riverdropformdao;


@GetMapping
("/create")
public Object create(@RequestParam(name = "Object") Object Object){
  return riverdropformdao.create(Object);
}


@GetMapping
("/update")
public Object update(@RequestParam(name = "Object") Object Object){
  return riverdropformdao.update(Object);
}


@GetMapping
("/delete")
public Object delete(@RequestParam(name = "Object") Object Object){
  return riverdropformdao.delete(Object);
}


}