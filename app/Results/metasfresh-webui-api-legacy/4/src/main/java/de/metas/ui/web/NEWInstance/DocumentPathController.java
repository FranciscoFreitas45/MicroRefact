package de.metas.ui.web.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class DocumentPathController {

 private DocumentPath documentpath;


@GetMapping
("/includedDocumentPath")
public DocumentPath includedDocumentPath(@RequestParam(name = "windowId") WindowId windowId,@RequestParam(name = "documentId") DocumentId documentId,@RequestParam(name = "detailId") DetailId detailId){
  return documentpath.includedDocumentPath(windowId,documentId,detailId);
}


@GetMapping
("/rootDocumentPath")
public DocumentPath rootDocumentPath(@RequestParam(name = "documentType") DocumentType documentType,@RequestParam(name = "documentTypeId") DocumentId documentTypeId,@RequestParam(name = "documentId") DocumentId documentId){
  return documentpath.rootDocumentPath(documentType,documentTypeId,documentId);
}


@GetMapping
("/getDocumentId")
public DocumentId getDocumentId(){
  return documentpath.getDocumentId();
}


@GetMapping
("/isRootDocument")
public boolean isRootDocument(){
  return documentpath.isRootDocument();
}


}