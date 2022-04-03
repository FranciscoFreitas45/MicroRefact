package de.metas.ui.web.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ASIDocumentController {

 private ASIDocument asidocument;


@GetMapping
("/getDocumentId")
public DocumentId getDocumentId(){
  return asidocument.getDocumentId();
}


@GetMapping
("/getFieldViews")
public Collection<IDocumentFieldView> getFieldViews(){
  return asidocument.getFieldViews();
}


@GetMapping
("/stream")
public Object stream(@RequestParam(name = "Object") Object Object){
  return asidocument.stream(Object);
}


@GetMapping
("/map")
public Object map(@RequestParam(name = "Object") Object Object){
  return asidocument.map(Object);
}


}