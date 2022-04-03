package upce.semprace.eshop.Controller;
 import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import upce.semprace.eshop.dto.PridejZmenNakupDto;
import upce.semprace.eshop.DTO.Uzivatel;
import upce.semprace.eshop.DTO.UserPrinciple;
import upce.semprace.eshop.services.CartService;
import java.util.Optional;
import upce.semprace.eshop.Interface.UzivatelRepository;
import upce.semprace.eshop.DTO.UserPrinciple;
@RestController
@RequestMapping("/cart")
@CrossOrigin("http://localhost:3000")
public class NakoupenaPolozkaController {

@Autowired
 private CartService cartService;

@Autowired
 private UzivatelRepository uzivatelRepository;


@PostMapping(value = { "", "/" })
public String order(PridejZmenNakupDto pridejZmenNakupDto){
    UserPrinciple principle;
    Optional<Uzivatel> user = null;
    try {
        principle = (UserPrinciple) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        user = uzivatelRepository.findByUsername(principle.getUsername());
    } catch (Exception ignored) {
    }
    Long idUzivatel;
    if (user != null && user.isPresent()) {
        idUzivatel = uzivatelRepository.findById(user.get().getId()).get().getId();
    } else {
        idUzivatel = uzivatelRepository.findByUsername("xxxx").get().getId();
    }
    cartService.order(idUzivatel, pridejZmenNakupDto.getDoprava(), pridejZmenNakupDto.getPlatba(), pridejZmenNakupDto.getPolozky());
    return "order";
}


}