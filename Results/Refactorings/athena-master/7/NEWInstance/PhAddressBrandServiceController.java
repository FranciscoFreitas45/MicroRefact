import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;
@AllArgsConstructor
@RestController
@CrossOrigin
public class PhAddressBrandServiceController {

 private PhAddressBrandService phaddressbrandservice;


@GetMapping
("/findOne")
public PhAddressBrand findOne(@RequestParam(name = "id") Long id){
  return phaddressbrandservice.findOne(id);
}


}