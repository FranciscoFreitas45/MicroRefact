package com.qidian.hcm.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class PositionRepositoryController {

 private PositionRepository positionrepository;


@GetMapping
("/findAllByDepartmentIdInAndEnableAndDeleted")
public List<PositionEntity> findAllByDepartmentIdInAndEnableAndDeleted(@RequestParam(name = "departmentIds") List<Long> departmentIds,@RequestParam(name = "enable") YesNo enable,@RequestParam(name = "deleted") YesNo deleted){
  return positionrepository.findAllByDepartmentIdInAndEnableAndDeleted(departmentIds,enable,deleted);
}


@GetMapping
("/findAllByDepartmentIdAndEnableAndDeleted")
public List<PositionEntity> findAllByDepartmentIdAndEnableAndDeleted(@RequestParam(name = "departmentId") Long departmentId,@RequestParam(name = "enable") YesNo enable,@RequestParam(name = "deleted") YesNo deleted){
  return positionrepository.findAllByDepartmentIdAndEnableAndDeleted(departmentId,enable,deleted);
}


}