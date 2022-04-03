package upce.semprace.eshop.Controller;
 import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation;
import upce.semprace.eshop.dto.PridejZmenVyrobceDto;
import upce.semprace.eshop.entity.Produkt;
import upce.semprace.eshop.entity.Vyrobce;
import upce.semprace.eshop.repository.VyrobceRepository;
import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/vyrobce")
@CrossOrigin("http://localhost:3000")
public class VyrobceController {

@Autowired
 private VyrobceRepository vyrobceRepository;


@DeleteMapping("/{id}")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public String smazDopravu(Long id,Model model){
    Vyrobce odeber = vyrobceRepository.findById(id).get();
    vyrobceRepository.deleteById(id);
    return odeber.getNazev();
}


@GetMapping(value = { "", "/" })
public List<Vyrobce> getProducts(){
    return vyrobceRepository.findAll();
}


@PostMapping(value = { "", "/" })
@PreAuthorize("hasRole('ROLE_ADMIN')")
public Optional<Vyrobce> zpracujVyrobce(PridejZmenVyrobceDto pridejZmenVyrobceDto){
    Vyrobce vyrobce = new Vyrobce();
    vyrobce.setId(pridejZmenVyrobceDto.getId());
    vyrobce.setNazev(pridejZmenVyrobceDto.getNazev());
    vyrobce.setAdresa(pridejZmenVyrobceDto.getAdresa());
    vyrobceRepository.save(vyrobce);
    return vyrobceRepository.findById(vyrobce.getId());
}


@ExceptionHandler(RuntimeException.class)
public String ochranaChyb(){
    return "chyba";
}


}