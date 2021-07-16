import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;
@AllArgsConstructor
@RestController
@CrossOrigin
public class RTableController {

 private RTableRepository rtablerepository;


@PutMapping
("/setStatus")
public void setStatus(@PathVariable(name = "id") Long id,@RequestParam(name = "status") String status){
 rtablerepository.setStatus(id,status);
}


}