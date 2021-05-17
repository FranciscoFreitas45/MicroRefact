import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;
@AllArgsConstructor
@RestController
@CrossOrigin
public class QmsProductionInspectionRepositoryController {

 private QmsProductionInspectionRepository qmsproductioninspectionrepository;


@GetMapping
("/findByMaterielIdAndSerialNumber")
public List<QmsProductionInspection> findByMaterielIdAndSerialNumber(@RequestParam(name = "s") Integer s,@RequestParam(name = "m") String m){
  return qmsproductioninspectionrepository.findByMaterielIdAndSerialNumber(s,m);
}


}