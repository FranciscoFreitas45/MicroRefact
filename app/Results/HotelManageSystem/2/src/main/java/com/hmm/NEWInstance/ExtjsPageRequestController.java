package com.hmm.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ExtjsPageRequestController {

 private ExtjsPageRequest extjspagerequest;

 private ExtjsPageRequest extjspagerequest;


@PutMapping
("/setLimit")
public void setLimit(@RequestParam(name = "limit") int limit){
extjspagerequest.setLimit(limit);
}


}