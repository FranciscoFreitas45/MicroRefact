package upce.semprace.eshop.NEWInstance;
 import org.springframework.web.bind.annotation.*;
 import upce.semprace.eshop.repository.NakoupenaPolozkaRepository;
  import java.util.*;
  import upce.semprace.eshop.DTO.*;
  import upce.semprace.eshop.entity.*;
  import org.springframework.beans.factory.annotation.Autowired;

@RestController
@CrossOrigin
public class NakoupenaPolozkaController {
@Autowired
 private NakoupenaPolozkaRepository nakoupenapolozkarepository;


@PutMapping
("/setMnozstvi/{id}")
public void setMnozstvi(@PathVariable(name = "id") Long id,@RequestParam(name = "mnozstvi") Integer mnozstvi){
 nakoupenapolozkarepository.setMnozstvi(id,mnozstvi);
}


@PutMapping
("/setNakup/{id}")
public void setNakup(@PathVariable(name = "id") Long id,@RequestParam(name = "nakup") Nakup nakup){
 nakoupenapolozkarepository.setNakup(id,nakup);
}


}