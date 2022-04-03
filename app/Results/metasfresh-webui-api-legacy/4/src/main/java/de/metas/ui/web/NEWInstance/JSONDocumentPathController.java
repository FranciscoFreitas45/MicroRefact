package de.metas.ui.web.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class JSONDocumentPathController {

 private JSONDocumentPath jsondocumentpath;


@GetMapping
("/ofWindowDocumentPath")
public JSONDocumentPath ofWindowDocumentPath(@RequestParam(name = "documentPath") DocumentPath documentPath,@RequestParam(name = "fieldName") String fieldName){
  return jsondocumentpath.ofWindowDocumentPath(documentPath,fieldName);
}


}