import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;
@AllArgsConstructor
@RestController
@CrossOrigin
public class PhBrandServiceController {

 private PhBrandService phbrandservice;


@GetMapping
("/findAll")
public List<PhBrand> findAll(@RequestParam(name = "prefix") String prefix){
  return phbrandservice.findAll(prefix);
}


}