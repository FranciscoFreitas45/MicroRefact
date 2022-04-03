package org.jugbd.mnet.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.jugbd.mnet.domain.Register;
@RestController
@CrossOrigin
public class RegisterComplicationManagementController {

@Autowired
 private RegisterComplicationManagementService registercomplicationmanagementservice;


@PutMapping
("/ComplicationManagement/{id}/Register/setRegister")
public void setRegister(@PathVariable(name="id") Long id,@RequestBody Register register){
registercomplicationmanagementservice.setRegister(id,register);
}


@GetMapping
("/ComplicationManagement/{id}/Register/getRegister")
public Register getRegister(@PathVariable(name="id") Long id){
return registercomplicationmanagementservice.getRegister(id);
}


}