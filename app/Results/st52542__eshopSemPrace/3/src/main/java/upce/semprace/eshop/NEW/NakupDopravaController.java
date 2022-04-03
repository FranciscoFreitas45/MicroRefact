package upce.semprace.eshop.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import upce.semprace.eshop.entity.Nakup;
@RestController
@CrossOrigin
public class NakupDopravaController {

@Autowired
 private NakupDopravaService nakupdopravaservice;


@GetMapping
("/Doprava/{id}/Nakup/getNakup")
public Set<Nakup> getNakup(@PathVariable(name="id") Long id){
return nakupdopravaservice.getNakup(id);
}


@PutMapping
("/Doprava/{id}/Nakup/setNakup")
public void setNakup(@PathVariable(name="id") Long id,@RequestBody Set<Nakup> nakup){
nakupdopravaservice.setNakup(id,nakup);
}


}