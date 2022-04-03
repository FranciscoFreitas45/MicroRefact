package net.shangtech.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class PanoramaDaoController {

 private PanoramaDao panoramadao;


@GetMapping
("/insert")
public Object insert(@RequestParam(name = "Object") Object Object){
  return panoramadao.insert(Object);
}


@GetMapping
("/find")
public Object find(@RequestParam(name = "Object") Object Object){
  return panoramadao.find(Object);
}


}