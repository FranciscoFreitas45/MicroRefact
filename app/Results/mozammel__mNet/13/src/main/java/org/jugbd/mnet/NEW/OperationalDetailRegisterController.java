package org.jugbd.mnet.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.jugbd.mnet.domain.OperationalDetail;
@RestController
@CrossOrigin
public class OperationalDetailRegisterController {

@Autowired
 private OperationalDetailRegisterService operationaldetailregisterservice;


@GetMapping
("/Register/{id}/OperationalDetail/getOperationalDetails")
public Set<OperationalDetail> getOperationalDetails(@PathVariable(name="id") Long id){
return operationaldetailregisterservice.getOperationalDetails(id);
}


@PutMapping
("/Register/{id}/OperationalDetail/setOperationalDetails")
public void setOperationalDetails(@PathVariable(name="id") Long id,@RequestBody Set<OperationalDetail> operationalDetails){
operationaldetailregisterservice.setOperationalDetails(id,operationalDetails);
}


}