import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;
@AllArgsConstructor
@RestController
@CrossOrigin
public class DepartmentRepoController {

 private DepartmentRepo departmentrepo;


@GetMapping
("/findByNameAndCompanyIdAndDepartIdNot")
public List<Department> findByNameAndCompanyIdAndDepartIdNot(@RequestParam(name = "trim") String trim,@RequestParam(name = "compId") int compId,@RequestParam(name = "primaryKey") int primaryKey){
  return departmentrepo.findByNameAndCompanyIdAndDepartIdNot(trim,compId,primaryKey);
}


@GetMapping
("/findByNameAndCompanyId")
public List<Department> findByNameAndCompanyId(@RequestParam(name = "dept") String dept,@RequestParam(name = "compId") int compId){
  return departmentrepo.findByNameAndCompanyId(dept,compId);
}


}