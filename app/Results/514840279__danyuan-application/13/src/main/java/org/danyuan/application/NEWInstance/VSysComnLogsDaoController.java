package org.danyuan.application.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class VSysComnLogsDaoController {

 private VSysComnLogsDao vsyscomnlogsdao;


@GetMapping
("/findAll")
public Object findAll(@RequestParam(name = "Object") Object Object){
  return vsyscomnlogsdao.findAll(Object);
}


}