package upce.semprace.eshop.NEWInstance;
 import org.springframework.web.bind.annotation.*;
  import upce.semprace.eshop.repository.NakupRepository;
  import java.util.*;
  import upce.semprace.eshop.DTO.*;
  import org.springframework.beans.factory.annotation.Autowired;

@RestController
@CrossOrigin
public class NakupController2 {

@Autowired
 private NakupRepository nakuprepository;


@PutMapping
("/setUzivatel/{id}")
public void setUzivatel(@PathVariable(name = "id") Long id,@RequestParam(name = "uzivatel") Uzivatel uzivatel){
 nakuprepository.setUzivatel(id,uzivatel);
}


@PutMapping
("/setPlatba/{id}")
public void setPlatba(@PathVariable(name = "id") Long id,@RequestParam(name = "platba") Platba platba){
 nakuprepository.setPlatba(id,platba);
}


@PutMapping
("/setDatumVytvoreni/{id}")
public void setDatumVytvoreni(@PathVariable(name = "id") Long id,@RequestParam(name = "datumVytvoreni") Date datumVytvoreni){
 nakuprepository.setDatumVytvoreni(id,datumVytvoreni);
}


@PutMapping
("/setObjednavka/{id}")
public void setObjednavka(@PathVariable(name = "id") Long id,@RequestParam(name = "objednavka") Integer objednavka){
 nakuprepository.setObjednavka(id,objednavka);
}


@PutMapping
("/setStav/{id}")
public void setStav(@PathVariable(name = "id") Long id,@RequestParam(name = "stav") Boolean stav){
 nakuprepository.setStav(id,stav);
}


}