package com.weflors.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ContragentsServiceImplController {

 private ContragentsServiceImpl contragentsserviceimpl;


@GetMapping
("/findAllContragents")
public List<ContragentsEntity> findAllContragents(){
  return contragentsserviceimpl.findAllContragents();
}


@GetMapping
("/loadContragentByContragentID")
public ContragentsEntity loadContragentByContragentID(@RequestParam(name = "contragentId") Integer contragentId){
  return contragentsserviceimpl.loadContragentByContragentID(contragentId);
}


}