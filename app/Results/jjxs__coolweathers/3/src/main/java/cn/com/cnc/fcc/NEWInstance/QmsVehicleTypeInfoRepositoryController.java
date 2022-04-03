package cn.com.cnc.fcc.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class QmsVehicleTypeInfoRepositoryController {

 private QmsVehicleTypeInfoRepository qmsvehicletypeinforepository;


@GetMapping
("/findByFlagStatusAndId")
public List<QmsVehicleTypeInfo> findByFlagStatusAndId(@RequestParam(name = "flagStatus") String flagStatus,@RequestParam(name = "vehicleId") Long vehicleId){
  return qmsvehicletypeinforepository.findByFlagStatusAndId(flagStatus,vehicleId);
}


@GetMapping
("/findByFlagStatusAndVehicleTypeOrderById")
public Optional<QmsVehicleTypeInfo> findByFlagStatusAndVehicleTypeOrderById(@RequestParam(name = "id") String id,@RequestParam(name = "string") String string){
  return qmsvehicletypeinforepository.findByFlagStatusAndVehicleTypeOrderById(id,string);
}


@GetMapping
("/findByFlagStatusAndVehicleType")
public List<QmsVehicleTypeInfo> findByFlagStatusAndVehicleType(@RequestParam(name = "string") String string,@RequestParam(name = "string2") String string2){
  return qmsvehicletypeinforepository.findByFlagStatusAndVehicleType(string,string2);
}


@GetMapping
("/findByVehicleTypeAndFlagStatus")
public QmsVehicleTypeInfo findByVehicleTypeAndFlagStatus(@RequestParam(name = "vehicleType") String vehicleType,@RequestParam(name = "flagStatus") String flagStatus){
  return qmsvehicletypeinforepository.findByVehicleTypeAndFlagStatus(vehicleType,flagStatus);
}


}