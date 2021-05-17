import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;
@AllArgsConstructor
@RestController
@CrossOrigin
public class ShiftMasterRepositoryController {

 private ShiftMasterRepository shiftmasterrepository;


@GetMapping
("/findByStatus")
public List<ShiftMaster> findByStatus(@RequestParam(name = "i") int i){
  return shiftmasterrepository.findByStatus(i);
}


}