package de.metas.ui.web.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class DocumentDescriptorFactoryController {

 private DocumentDescriptorFactory documentdescriptorfactory;


@GetMapping
("/getTableRecordReference")
public TableRecordReference getTableRecordReference(@RequestParam(name = "documentPath") DocumentPath documentPath){
  return documentdescriptorfactory.getTableRecordReference(documentPath);
}


@GetMapping
("/getDocumentEntityDescriptor")
public DocumentEntityDescriptor getDocumentEntityDescriptor(@RequestParam(name = "documentPath") DocumentPath documentPath){
  return documentdescriptorfactory.getDocumentEntityDescriptor(documentPath);
}


@GetMapping
("/getTableNameOrNull")
public String getTableNameOrNull(@RequestParam(name = "AD_Window_ID") int AD_Window_ID,@RequestParam(name = "detailId") DetailId detailId){
  return documentdescriptorfactory.getTableNameOrNull(AD_Window_ID,detailId);
}


@PutMapping
("/invalidateForWindow")
public void invalidateForWindow(@RequestParam(name = "windowId") WindowId windowId){
documentdescriptorfactory.invalidateForWindow(windowId);
}


@GetMapping
("/getDocumentDescriptor")
public DocumentDescriptor getDocumentDescriptor(@RequestParam(name = "windowId") WindowId windowId){
  return documentdescriptorfactory.getDocumentDescriptor(windowId);
}


}