package de.metas.ui.web.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class DocumentCollectionController {

 private DocumentCollection documentcollection;


@GetMapping
("/isValidDocumentPath")
public boolean isValidDocumentPath(@RequestParam(name = "documentPath") DocumentPath documentPath){
  return documentcollection.isValidDocumentPath(documentPath);
}


@GetMapping
("/forDocumentReadonly")
public R forDocumentReadonly(@RequestParam(name = "documentPath") DocumentPath documentPath,@RequestParam(name = "documentProcessor") Function<Document,R> documentProcessor){
  return documentcollection.forDocumentReadonly(documentPath,documentProcessor);
}


}