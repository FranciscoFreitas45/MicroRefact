import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;
@AllArgsConstructor
@RestController
@CrossOrigin
public class DesignationRepoController {

 private DesignationRepo designationrepo;


@GetMapping
("/findByNameAndCompanyIdAndDesigIdNot")
public List<Designation> findByNameAndCompanyIdAndDesigIdNot(@RequestParam(name = "trim") String trim,@RequestParam(name = "compId") int compId,@RequestParam(name = "primaryKey") int primaryKey){
  return designationrepo.findByNameAndCompanyIdAndDesigIdNot(trim,compId,primaryKey);
}


@GetMapping
("/findByNameAndCompanyId")
public List<Designation> findByNameAndCompanyId(@RequestParam(name = "desgn") String desgn,@RequestParam(name = "compId") int compId){
  return designationrepo.findByNameAndCompanyId(desgn,compId);
}


}