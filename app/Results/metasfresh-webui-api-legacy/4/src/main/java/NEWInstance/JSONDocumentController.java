package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class JSONDocumentController {

 private JSONDocument jsondocument;

 private JSONDocument jsondocument;


@GetMapping
("/ofEvents")
public List<JSONDocument> ofEvents(@RequestParam(name = "documentChangesCollector") IDocumentChangesCollector documentChangesCollector,@RequestParam(name = "options") JSONDocumentOptions options){
  return jsondocument.ofEvents(documentChangesCollector,options);
}


@GetMapping
("/ofDocument")
public JSONDocument ofDocument(@RequestParam(name = "document") Document document,@RequestParam(name = "options") JSONDocumentOptions options){
  return jsondocument.ofDocument(document,options);
}


}