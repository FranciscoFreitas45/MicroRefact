package upce.semprace.eshop.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import upce.semprace.eshop.entity.Platba;
@RestController
@CrossOrigin
public class PlatbaNakupController {

@Autowired
 private PlatbaNakupService platbanakupservice;


@GetMapping
("/Nakup/{id}/Platba/getPlatba")
public Platba getPlatba(@PathVariable(name="id") Long id){
return platbanakupservice.getPlatba(id);
}


@PutMapping
("/Nakup/{id}/Platba/setPlatba")
public void setPlatba(@PathVariable(name="id") Long id,@RequestBody Platba platba){
platbanakupservice.setPlatba(id,platba);
}


}