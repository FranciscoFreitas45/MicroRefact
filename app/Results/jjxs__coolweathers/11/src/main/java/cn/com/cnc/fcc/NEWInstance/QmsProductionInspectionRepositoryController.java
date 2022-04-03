package cn.com.cnc.fcc.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class QmsProductionInspectionRepositoryController {

 private QmsProductionInspectionRepository qmsproductioninspectionrepository;


@GetMapping
("/findByBomTechnologyIdAndFlagStatus")
public List<QmsProductionInspection> findByBomTechnologyIdAndFlagStatus(@RequestParam(name = "BomTechnologyId") Integer BomTechnologyId,@RequestParam(name = "FlagStatus") String FlagStatus){
  return qmsproductioninspectionrepository.findByBomTechnologyIdAndFlagStatus(BomTechnologyId,FlagStatus);
}


@GetMapping
("/findByMaterielIdAndSerialNumber")
public List<QmsProductionInspection> findByMaterielIdAndSerialNumber(@RequestParam(name = "s") Integer s,@RequestParam(name = "m") String m){
  return qmsproductioninspectionrepository.findByMaterielIdAndSerialNumber(s,m);
}


}