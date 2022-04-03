package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class DocumentFilterParamController {

 private DocumentFilterParam documentfilterparam;

 private DocumentFilterParam documentfilterparam;


@GetMapping
("/isSqlFilter")
public boolean isSqlFilter(){
  return documentfilterparam.isSqlFilter();
}


}