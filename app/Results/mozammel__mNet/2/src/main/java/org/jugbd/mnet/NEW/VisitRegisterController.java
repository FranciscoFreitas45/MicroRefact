package org.jugbd.mnet.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.jugbd.mnet.domain.Visit;
@RestController
@CrossOrigin
public class VisitRegisterController {

@Autowired
 private VisitRegisterService visitregisterservice;


@GetMapping
("/Register/{id}/Visit/getVisits")
public Set<Visit> getVisits(@PathVariable(name="id") Long id){
return visitregisterservice.getVisits(id);
}


@PutMapping
("/Register/{id}/Visit/setVisits")
public void setVisits(@PathVariable(name="id") Long id,@RequestBody Set<Visit> visits){
visitregisterservice.setVisits(id,visits);
}


}