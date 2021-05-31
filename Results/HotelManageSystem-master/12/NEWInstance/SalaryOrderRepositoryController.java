import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;
@AllArgsConstructor
@RestController
@CrossOrigin
public class SalaryOrderRepositoryController {

 private SalaryOrderRepository salaryorderrepository;


@GetMapping
("/findSalaryByDay")
public Float findSalaryByDay(@RequestParam(name = "date") String date){
  return salaryorderrepository.findSalaryByDay(date);
}


}