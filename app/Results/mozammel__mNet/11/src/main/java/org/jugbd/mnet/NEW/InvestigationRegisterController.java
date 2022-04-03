package org.jugbd.mnet.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.jugbd.mnet.domain.Investigation;
@RestController
@CrossOrigin
public class InvestigationRegisterController {

@Autowired
 private InvestigationRegisterService investigationregisterservice;


@GetMapping
("/Register/{id}/Investigation/getInvestigation")
public Set<Investigation> getInvestigation(@PathVariable(name="id") Long id){
return investigationregisterservice.getInvestigation(id);
}


@PutMapping
("/Register/{id}/Investigation/setInvestigation")
public void setInvestigation(@PathVariable(name="id") Long id,@RequestBody Set<Investigation> investigation){
investigationregisterservice.setInvestigation(id,investigation);
}


}