import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;
@AllArgsConstructor
@RestController
@CrossOrigin
public class SectorRepositoryController {

 private SectorRepository sectorrepository;


@GetMapping
("/findByIC_Type")
public List<Object[]> findByIC_Type(@RequestParam(name = "IC_Type") String IC_Type){
  return sectorrepository.findByIC_Type(IC_Type);
}


}