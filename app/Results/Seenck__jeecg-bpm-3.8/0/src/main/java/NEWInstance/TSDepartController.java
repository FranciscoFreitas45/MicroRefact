package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class TSDepartController {

 private TSDepart tsdepart;

 private TSDepart tsdepart;


@PutMapping
("/setOrgCode")
public void setOrgCode(@RequestParam(name = "orgCode") String orgCode){
tsdepart.setOrgCode(orgCode);
}


}