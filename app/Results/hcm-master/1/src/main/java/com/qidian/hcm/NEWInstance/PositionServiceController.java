package com.qidian.hcm.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class PositionServiceController {

 private PositionService positionservice;


@GetMapping
("/getPositionByDepartmentId")
public List<PositionGradeDTO> getPositionByDepartmentId(@RequestParam(name = "id") Long id){
  return positionservice.getPositionByDepartmentId(id);
}


}