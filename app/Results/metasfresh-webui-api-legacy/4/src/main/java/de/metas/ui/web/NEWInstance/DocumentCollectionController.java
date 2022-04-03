package de.metas.ui.web.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class DocumentCollectionController {

 private DocumentCollection documentcollection;


@GetMapping
("/getTableRecordReference")
public TableRecordReference getTableRecordReference(@RequestParam(name = "documentPath") DocumentPath documentPath){
  return documentcollection.getTableRecordReference(documentPath);
}


@GetMapping
("/forDocumentWritable")
public R forDocumentWritable(@RequestParam(name = "documentPath") DocumentPath documentPath,@RequestParam(name = "changesCollector") IDocumentChangesCollector changesCollector,@RequestParam(name = "documentProcessor") Function<Document,R> documentProcessor){
  return documentcollection.forDocumentWritable(documentPath,changesCollector,documentProcessor);
}


@GetMapping
("/getDocumentEntityDescriptor")
public DocumentEntityDescriptor getDocumentEntityDescriptor(@RequestParam(name = "windowId") WindowId windowId){
  return documentcollection.getDocumentEntityDescriptor(windowId);
}


@GetMapping
("/forRootDocumentReadonly")
public R forRootDocumentReadonly(@RequestParam(name = "documentPath") DocumentPath documentPath,@RequestParam(name = "rootDocumentProcessor") Function<Document,R> rootDocumentProcessor){
  return documentcollection.forRootDocumentReadonly(documentPath,rootDocumentProcessor);
}


@GetMapping
("/forDocumentReadonly")
public R forDocumentReadonly(@RequestParam(name = "documentPath") DocumentPath documentPath,@RequestParam(name = "documentProcessor") Function<Document,R> documentProcessor){
  return documentcollection.forDocumentReadonly(documentPath,documentProcessor);
}


@GetMapping
("/forRootDocumentWritable")
public R forRootDocumentWritable(@RequestParam(name = "documentPathOrNew") DocumentPath documentPathOrNew,@RequestParam(name = "changesCollector") IDocumentChangesCollector changesCollector,@RequestParam(name = "rootDocumentProcessor") Function<Document,R> rootDocumentProcessor){
  return documentcollection.forRootDocumentWritable(documentPathOrNew,changesCollector,rootDocumentProcessor);
}


@GetMapping
("/createBoilerPlateContext")
public BoilerPlateContext createBoilerPlateContext(@RequestParam(name = "documentPath") DocumentPath documentPath){
  return documentcollection.createBoilerPlateContext(documentPath);
}


@PutMapping
("/invalidateRootDocument")
public void invalidateRootDocument(@RequestParam(name = "documentPath") DocumentPath documentPath){
documentcollection.invalidateRootDocument(documentPath);
}


}