package de.metas.ui.web.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class WindowIdController {

 private WindowId windowid;


@GetMapping
("/fromJson")
public WindowId fromJson(@RequestParam(name = "json") String json){
  return windowid.fromJson(json);
}


@GetMapping
("/toAdWindowId")
public AdWindowId toAdWindowId(){
  return windowid.toAdWindowId();
}


@GetMapping
("/fromNullableJson")
public WindowId fromNullableJson(@RequestParam(name = "json") String json){
  return windowid.fromNullableJson(json);
}


@GetMapping
("/isInt")
public boolean isInt(){
  return windowid.isInt();
}


@GetMapping
("/toDocumentId")
public DocumentId toDocumentId(){
  return windowid.toDocumentId();
}


@GetMapping
("/toAdWindowIdOrNull")
public AdWindowId toAdWindowIdOrNull(){
  return windowid.toAdWindowIdOrNull();
}


}