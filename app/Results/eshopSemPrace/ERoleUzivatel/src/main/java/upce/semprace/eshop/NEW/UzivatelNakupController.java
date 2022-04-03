package upce.semprace.eshop.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import upce.semprace.eshop.entity.Uzivatel;
@RestController
@CrossOrigin
public class UzivatelNakupController {

@Autowired
 private UzivatelNakupService uzivatelnakupservice;


@PutMapping
("/Nakup/{id}/Uzivatel/setUzivatel")
public void setUzivatel(@PathVariable(name="id") Long id,@RequestBody Uzivatel uzivatel){
uzivatelnakupservice.setUzivatel(id,uzivatel);
}


@GetMapping
("/Nakup/{id}/Uzivatel/getUzivatel")
public Uzivatel getUzivatel(@PathVariable(name="id") Long id){
return uzivatelnakupservice.getUzivatel(id);
}


}