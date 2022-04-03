package upce.semprace.eshop.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import upce.semprace.eshop.entity.Produkt;
@RestController
@CrossOrigin
public class ProduktNakoupenaPolozkaController {

@Autowired
 private ProduktNakoupenaPolozkaService produktnakoupenapolozkaservice;


@PutMapping
("/NakoupenaPolozka/{id}/Produkt/setProdukt")
public void setProdukt(@PathVariable(name="id") Long id,@RequestBody Produkt produkt){
produktnakoupenapolozkaservice.setProdukt(id,produkt);
}


@GetMapping
("/NakoupenaPolozka/{id}/Produkt/getProdukt")
public Produkt getProdukt(@PathVariable(name="id") Long id){
return produktnakoupenapolozkaservice.getProdukt(id);
}


}