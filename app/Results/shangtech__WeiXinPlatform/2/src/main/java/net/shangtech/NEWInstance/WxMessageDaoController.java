package net.shangtech.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class WxMessageDaoController {

 private WxMessageDao wxmessagedao;


@GetMapping
("/find")
public Object find(@RequestParam(name = "Object") Object Object){
  return wxmessagedao.find(Object);
}


@GetMapping
("/insert")
public Object insert(@RequestParam(name = "Object") Object Object){
  return wxmessagedao.insert(Object);
}


@GetMapping
("/update")
public Object update(@RequestParam(name = "Object") Object Object){
  return wxmessagedao.update(Object);
}


}