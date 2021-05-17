import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;
@AllArgsConstructor
@RestController
@CrossOrigin
public class QmsMaterielSupplierRepositoryController {

 private QmsMaterielSupplierRepository qmsmaterielsupplierrepository;


@GetMapping
("/findBySupplierId")
public List<QmsMaterielSupplier> findBySupplierId(@RequestParam(name = "s") Integer s){
  return qmsmaterielsupplierrepository.findBySupplierId(s);
}


}