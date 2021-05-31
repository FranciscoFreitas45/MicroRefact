import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;
@AllArgsConstructor
@RestController
@CrossOrigin
public class EmployeeMasterRepositoryController {

 private EmployeeMasterRepository employeemasterrepository;


@GetMapping
("/findByDelStatusAndCmpCodeAndSubCmpIdOrderByEmpIdDesc")
public List<EmployeeMaster> findByDelStatusAndCmpCodeAndSubCmpIdOrderByEmpIdDesc(@RequestParam(name = "del") int del,@RequestParam(name = "companyId") int companyId,@RequestParam(name = "subCompId") int subCompId){
  return employeemasterrepository.findByDelStatusAndCmpCodeAndSubCmpIdOrderByEmpIdDesc(del,companyId,subCompId);
}


}