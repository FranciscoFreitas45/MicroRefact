package de.metas.ui.web.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class DocumentIdController {

 private DocumentId documentid;


@GetMapping
("/toJson")
public String toJson(){
  return documentid.toJson();
}


@GetMapping
("/ofString")
public DocumentId ofString(@RequestParam(name = "idStr") String idStr){
  return documentid.ofString(idStr);
}


@GetMapping
("/toInt")
public int toInt(){
  return documentid.toInt();
}


@GetMapping
("/isInt")
public boolean isInt(){
  return documentid.isInt();
}


@GetMapping
("/toIntOr")
public int toIntOr(@RequestParam(name = "fallbackValue") int fallbackValue){
  return documentid.toIntOr(fallbackValue);
}


@GetMapping
("/of")
public DocumentId of(@RequestParam(name = "id") RepoIdAware id){
  return documentid.of(id);
}


@GetMapping
("/ofComposedKeyParts")
public DocumentId ofComposedKeyParts(@RequestParam(name = "composedKeyParts") List<Object> composedKeyParts){
  return documentid.ofComposedKeyParts(composedKeyParts);
}


@GetMapping
("/equals")
public boolean equals(@RequestParam(name = "obj") Object obj){
  return documentid.equals(obj);
}


}