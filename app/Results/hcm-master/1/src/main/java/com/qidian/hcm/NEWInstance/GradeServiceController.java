package com.qidian.hcm.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class GradeServiceController {

 private GradeService gradeservice;


@GetMapping
("/getIdNameMap")
public Map<Long,String> getIdNameMap(){
  return gradeservice.getIdNameMap();
}


}