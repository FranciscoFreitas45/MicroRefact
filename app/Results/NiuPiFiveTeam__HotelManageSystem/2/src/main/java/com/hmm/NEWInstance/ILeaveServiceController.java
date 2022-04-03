package com.hmm.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ILeaveServiceController {

 private ILeaveService ileaveservice;


@GetMapping
("/findTotalLeaveTimes")
public float findTotalLeaveTimes(@RequestParam(name = "userName") String userName){
  return ileaveservice.findTotalLeaveTimes(userName);
}


@GetMapping
("/findTatalPersonLeave")
public int findTatalPersonLeave(){
  return ileaveservice.findTatalPersonLeave();
}


@GetMapping
("/findByyearAndOntudytimeleave")
public List<Map<Object,Object>> findByyearAndOntudytimeleave(@RequestParam(name = "year") Integer year,@RequestParam(name = "userName") String userName){
  return ileaveservice.findByyearAndOntudytimeleave(year,userName);
}


@GetMapping
("/findleave")
public List<Map<Object,Object>> findleave(@RequestParam(name = "year") Integer year){
  return ileaveservice.findleave(year);
}


}