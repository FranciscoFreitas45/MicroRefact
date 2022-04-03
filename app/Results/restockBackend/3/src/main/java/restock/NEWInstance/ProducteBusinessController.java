package restock.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ProducteBusinessController {

 private ProducteBusiness productebusiness;


@GetMapping
("/getProductesPerProveidor")
public List<Producte> getProductesPerProveidor(@RequestParam(name = "provId") Integer provId){
  return productebusiness.getProductesPerProveidor(provId);
}


}