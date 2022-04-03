package com.gs.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class IncomingOutgoingServiceController {

 private IncomingOutgoingService incomingoutgoingservice;


@GetMapping
("/queryByCompanyIdForInType")
public List<IncomingOutgoing> queryByCompanyIdForInType(@RequestParam(name = "companyId") String companyId){
  return incomingoutgoingservice.queryByCompanyIdForInType(companyId);
}


@GetMapping
("/queryByCompanyIdForOutType")
public List<IncomingOutgoing> queryByCompanyIdForOutType(@RequestParam(name = "companyId") String companyId){
  return incomingoutgoingservice.queryByCompanyIdForOutType(companyId);
}


@GetMapping
("/insert")
public Object insert(@RequestParam(name = "Object") Object Object){
  return incomingoutgoingservice.insert(Object);
}


@PutMapping
("/addInsert")
public void addInsert(@RequestParam(name = "incomingOutgoings") List<IncomingOutgoing> incomingOutgoings){
incomingoutgoingservice.addInsert(incomingOutgoings);
}


}