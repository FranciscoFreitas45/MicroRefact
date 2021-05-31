import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;
@AllArgsConstructor
@RestController
@CrossOrigin
public class InStorageRepositoryController {

 private InStorageRepository instoragerepository;


@GetMapping
("/findInStorageOrderByDay")
public Float findInStorageOrderByDay(@RequestParam(name = "dateString") String dateString){
  return instoragerepository.findInStorageOrderByDay(dateString);
}


}