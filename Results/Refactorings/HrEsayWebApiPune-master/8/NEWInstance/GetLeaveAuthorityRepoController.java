import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;
@AllArgsConstructor
@RestController
@CrossOrigin
public class GetLeaveAuthorityRepoController {

 private GetLeaveAuthorityRepo getleaveauthorityrepo;


@GetMapping
("/getEmpReportingName")
public List<String> getEmpReportingName(@RequestParam(name = "reportIds") String[] reportIds){
  return getleaveauthorityrepo.getEmpReportingName(reportIds);
}


}