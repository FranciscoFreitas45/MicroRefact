import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;
@AllArgsConstructor
@RestController
@CrossOrigin
public class DeptServiceController {

 private DeptService deptservice;


@GetMapping
("/findByDeptName")
public Department findByDeptName(@RequestParam(name = "deptName") String deptName){
  return deptservice.findByDeptName(deptName);
}


}