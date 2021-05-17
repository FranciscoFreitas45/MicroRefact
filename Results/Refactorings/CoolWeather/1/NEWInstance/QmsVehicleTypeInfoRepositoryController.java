import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;
@AllArgsConstructor
@RestController
@CrossOrigin
public class QmsVehicleTypeInfoRepositoryController {

 private QmsVehicleTypeInfoRepository qmsvehicletypeinforepository;


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