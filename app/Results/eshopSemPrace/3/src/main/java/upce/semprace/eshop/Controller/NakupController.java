package upce.semprace.eshop.Controller;
 import org.hibernate.annotations.BatchSize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import upce.semprace.eshop.entity.NakoupenaPolozka;
import upce.semprace.eshop.entity.Nakup;
import upce.semprace.eshop.repository.NakoupenaPolozkaRepository;
import upce.semprace.eshop.repository.NakupRepository;
import javax.persistence.ManyToMany;
import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/nakup")
public class NakupController {

@Autowired
 private NakupRepository nakupRepository;

@Autowired
 private NakoupenaPolozkaRepository nakoupenaPolozkaRepository;


@GetMapping("/nakup-detail-polozky/{id}")
public List<NakoupenaPolozka> zobrazDetailyNakupuPolozky(Long id,Model model){
    return nakoupenaPolozkaRepository.findByNakupId(id);
}


@GetMapping("/nakup-zmen-stav/{id}")
public void zmenStav(Long id,Model model){
    nakupRepository.zmenStav(true, id);
}


@GetMapping(value = { "", "/" })
public List<Nakup> getNakupy(Model model){
    return nakupRepository.findAll();
}


@ExceptionHandler(RuntimeException.class)
public String ochranaChyb(){
    return "chyba";
}


@GetMapping("/nakup-detail/{id}")
public Nakup zobrazDetailyNakupu(Long id,Model model){
    return nakupRepository.findById(id).get();
}


}