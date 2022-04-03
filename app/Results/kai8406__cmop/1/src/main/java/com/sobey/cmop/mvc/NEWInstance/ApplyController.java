package com.sobey.cmop.mvc.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ApplyController {

 private Apply apply;

 private Apply apply;


@PutMapping
("/setPriority")
public void setPriority(@RequestParam(name = "priority") Integer priority){
apply.setPriority(priority);
}


@PutMapping
("/setServiceStart")
public void setServiceStart(@RequestParam(name = "serviceStart") String serviceStart){
apply.setServiceStart(serviceStart);
}


@PutMapping
("/setServiceEnd")
public void setServiceEnd(@RequestParam(name = "serviceEnd") String serviceEnd){
apply.setServiceEnd(serviceEnd);
}


@PutMapping
("/setDescription")
public void setDescription(@RequestParam(name = "description") String description){
apply.setDescription(description);
}


@PutMapping
("/setServiceTag")
public void setServiceTag(@RequestParam(name = "serviceTag") String serviceTag){
apply.setServiceTag(serviceTag);
}


}