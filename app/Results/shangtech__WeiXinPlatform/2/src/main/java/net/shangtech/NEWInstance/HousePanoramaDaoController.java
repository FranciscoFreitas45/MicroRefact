package net.shangtech.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class HousePanoramaDaoController {

 private HousePanoramaDao housepanoramadao;


@GetMapping
("/insert")
public Object insert(@RequestParam(name = "Object") Object Object){
  return housepanoramadao.insert(Object);
}


@GetMapping
("/find")
public Object find(@RequestParam(name = "Object") Object Object){
  return housepanoramadao.find(Object);
}


@GetMapping
("/delete")
public Object delete(@RequestParam(name = "Object") Object Object){
  return housepanoramadao.delete(Object);
}


}