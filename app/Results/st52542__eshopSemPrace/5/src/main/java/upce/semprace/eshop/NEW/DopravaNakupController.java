package upce.semprace.eshop.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import upce.semprace.eshop.entity.Doprava;
@RestController
@CrossOrigin
public class DopravaNakupController {

@Autowired
 private DopravaNakupService dopravanakupservice;


@PutMapping
("/Nakup/{id}/Doprava/setDoprava")
public void setDoprava(@PathVariable(name="id") Long id,@RequestBody Doprava doprava){
dopravanakupservice.setDoprava(id,doprava);
}


@GetMapping
("/Nakup/{id}/Doprava/getDoprava")
public Doprava getDoprava(@PathVariable(name="id") Long id){
return dopravanakupservice.getDoprava(id);
}


}