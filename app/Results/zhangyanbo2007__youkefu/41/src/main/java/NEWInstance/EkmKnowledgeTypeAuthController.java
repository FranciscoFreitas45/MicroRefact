package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class EkmKnowledgeTypeAuthController {

 private EkmKnowledgeTypeAuth ekmknowledgetypeauth;

 private EkmKnowledgeTypeAuth ekmknowledgetypeauth;


@GetMapping
("/isView")
public boolean isView(){
  return ekmknowledgetypeauth.isView();
}


@GetMapping
("/isCover")
public boolean isCover(){
  return ekmknowledgetypeauth.isCover();
}


}