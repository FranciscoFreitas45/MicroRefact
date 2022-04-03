package com.weflors.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ProcurementServiceImplController {

 private ProcurementServiceImpl procurementserviceimpl;


@GetMapping
("/saveProcurement")
public ProcurementEntity saveProcurement(@RequestParam(name = "procurementEntity") ProcurementEntity procurementEntity){
  return procurementserviceimpl.saveProcurement(procurementEntity);
}


@GetMapping
("/loadAllProcurements")
public List<ProcurementEntity> loadAllProcurements(){
  return procurementserviceimpl.loadAllProcurements();
}


@GetMapping
("/findAllProcurementsByProductDate")
public List<ProcurementEntity> findAllProcurementsByProductDate(@RequestParam(name = "productId") Integer productId,@RequestParam(name = "startDatePeriod") Timestamp startDatePeriod,@RequestParam(name = "endDatePeriod") Timestamp endDatePeriod){
  return procurementserviceimpl.findAllProcurementsByProductDate(productId,startDatePeriod,endDatePeriod);
}


}