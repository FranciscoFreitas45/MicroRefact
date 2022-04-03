package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class JSONDocumentLayoutOptionsController {

 private JSONDocumentLayoutOptions jsondocumentlayoutoptions;

 private JSONDocumentLayoutOptions jsondocumentlayoutoptions;


@GetMapping
("/of")
public JSONDocumentLayoutOptions of(@RequestParam(name = "userSession") UserSession userSession){
  return jsondocumentlayoutoptions.of(userSession);
}


}