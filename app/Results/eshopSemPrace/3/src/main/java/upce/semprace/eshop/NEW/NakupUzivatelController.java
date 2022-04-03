package upce.semprace.eshop.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import upce.semprace.eshop.entity.Nakup;
import java.util.*;
@RestController
@CrossOrigin
public class NakupUzivatelController {

@Autowired
 private NakupUzivatelService nakupuzivatelservice;


@GetMapping
("/Uzivatel/{id}/Nakup/getNakup")
public Set<Nakup> getNakup(@PathVariable(name="id") Long id){
return nakupuzivatelservice.getNakup(id);
}


@PutMapping
("/Uzivatel/{id}/Nakup/setNakup")
public void setNakup(@PathVariable(name="id") Long id,@RequestBody Set<Nakup> nakup){
nakupuzivatelservice.setNakup(id,nakup);
}


}