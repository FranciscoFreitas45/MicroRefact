package de.metas.ui.web.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class DocumentReferencesServiceController {

 private DocumentReferencesService documentreferencesservice;


@GetMapping
("/getDocumentReference")
public DocumentReference getDocumentReference(@RequestParam(name = "sourceDocumentPath") DocumentPath sourceDocumentPath,@RequestParam(name = "targetWindowId") WindowId targetWindowId){
  return documentreferencesservice.getDocumentReference(sourceDocumentPath,targetWindowId);
}


}