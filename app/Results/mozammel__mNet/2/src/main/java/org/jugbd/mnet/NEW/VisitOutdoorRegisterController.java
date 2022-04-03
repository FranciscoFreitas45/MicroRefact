package org.jugbd.mnet.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.jugbd.mnet.domain.Visit;
@RestController
@CrossOrigin
public class VisitOutdoorRegisterController {

@Autowired
 private VisitOutdoorRegisterService visitoutdoorregisterservice;


@GetMapping
("/OutdoorRegister/{id}/Visit/getVisits")
public Set<Visit> getVisits(@PathVariable(name="id") Long id){
return visitoutdoorregisterservice.getVisits(id);
}


@GetMapping
("/OutdoorRegister/{id}/Visit/setVisits")
public OutdoorRegister setVisits(@PathVariable(name="id") Long id,@RequestParam Set<Visit> visits){
return visitoutdoorregisterservice.setVisits(id,visits);
}


}