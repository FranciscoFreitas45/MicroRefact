package org.jugbd.mnet.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.jugbd.mnet.domain.ComplicationManagement;
@RestController
@CrossOrigin
public class ComplicationManagementRegisterController {

@Autowired
 private ComplicationManagementRegisterService complicationmanagementregisterservice;


@GetMapping
("/Register/{id}/ComplicationManagement/getComplicationManagement")
public ComplicationManagement getComplicationManagement(@PathVariable(name="id") Long id){
return complicationmanagementregisterservice.getComplicationManagement(id);
}


@PutMapping
("/Register/{id}/ComplicationManagement/setComplicationManagement")
public void setComplicationManagement(@PathVariable(name="id") Long id,@RequestBody ComplicationManagement complicationManagement){
complicationmanagementregisterservice.setComplicationManagement(id,complicationManagement);
}


}