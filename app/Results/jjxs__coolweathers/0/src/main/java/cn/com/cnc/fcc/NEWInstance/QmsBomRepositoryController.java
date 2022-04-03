package cn.com.cnc.fcc.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class QmsBomRepositoryController {

 private QmsBomRepository qmsbomrepository;


@GetMapping
("/findByVehicleIdAndFlagStatus")
public List<QmsBom> findByVehicleIdAndFlagStatus(@RequestParam(name = "vehicleType") Integer vehicleType,@RequestParam(name = "flagStatus") String flagStatus){
  return qmsbomrepository.findByVehicleIdAndFlagStatus(vehicleType,flagStatus);
}


}