package org.jugbd.mnet.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.jugbd.mnet.domain.OutdoorRegister;
@RestController
@CrossOrigin
public class OutdoorRegisterVisitController {

@Autowired
 private OutdoorRegisterVisitService outdoorregistervisitservice;


@GetMapping
("/Visit/{id}/OutdoorRegister/setOutdoorRegister")
public Visit setOutdoorRegister(@PathVariable(name="id") Long id,@RequestParam OutdoorRegister outdoorRegister){
return outdoorregistervisitservice.setOutdoorRegister(id,outdoorRegister);
}


@GetMapping
("/Visit/{id}/OutdoorRegister/getOutdoorRegister")
public OutdoorRegister getOutdoorRegister(@PathVariable(name="id") Long id){
return outdoorregistervisitservice.getOutdoorRegister(id);
}


}