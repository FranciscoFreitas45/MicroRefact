package upce.semprace.eshop.Controller;
 import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation;
import upce.semprace.eshop.dto.PridejZmenPlatbaDto;
import upce.semprace.eshop.entity.Doprava;
import upce.semprace.eshop.entity.Platba;
import upce.semprace.eshop.repository.PlatbaRepository;
import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/platba")
@CrossOrigin("http://localhost:3000")
public class PlatbaController {

@Autowired
 private PlatbaRepository platbaRepository;


@DeleteMapping("/{id}")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public String smazDopravu(Long id,Model model){
    Platba odeber = platbaRepository.findById(id).get();
    platbaRepository.deleteById(id);
    return odeber.getPopis();
}


@PostMapping(value = { "", "/" })
@PreAuthorize("hasRole('ROLE_ADMIN')")
public Optional<Platba> zpracujPlatbu(PridejZmenPlatbaDto pridejZmenPlatbaDto){
    Platba platba = new Platba();
    platba.setId(pridejZmenPlatbaDto.getId());
    platba.setPopis(pridejZmenPlatbaDto.getPopis());
    platba.setPrevod(pridejZmenPlatbaDto.getPrevod());
    platbaRepository.save(platba);
    return platbaRepository.findById(platba.getId());
}


@GetMapping(value = { "", "/" })
public List<Platba> getProducts(){
    return platbaRepository.findAll();
}


@ExceptionHandler(RuntimeException.class)
public String ochranaChyb(){
    return "chyba";
}


}