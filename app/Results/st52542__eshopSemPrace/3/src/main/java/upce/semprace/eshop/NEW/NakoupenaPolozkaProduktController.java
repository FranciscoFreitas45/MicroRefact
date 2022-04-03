package upce.semprace.eshop.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import upce.semprace.eshop.entity.NakoupenaPolozka;
@RestController
@CrossOrigin
public class NakoupenaPolozkaProduktController {

@Autowired
 private NakoupenaPolozkaProduktService nakoupenapolozkaproduktservice;


@PutMapping
("/Produkt/{id}/NakoupenaPolozka/setNakoupenaPolozka")
public void setNakoupenaPolozka(@PathVariable(name="id") Long id,@RequestBody NakoupenaPolozka nakoupenaPolozka){
nakoupenapolozkaproduktservice.setNakoupenaPolozka(id,nakoupenaPolozka);
}


@GetMapping
("/Produkt/{id}/NakoupenaPolozka/getNakoupenaPolozka")
public NakoupenaPolozka getNakoupenaPolozka(@PathVariable(name="id") Long id){
return nakoupenapolozkaproduktservice.getNakoupenaPolozka(id);
}


}