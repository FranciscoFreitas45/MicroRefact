package de.metas.ui.web.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class LookupValueController {

 private LookupValue lookupvalue;


@GetMapping
("/getDisplayNameTrl")
public ITranslatableString getDisplayNameTrl(){
  return lookupvalue.getDisplayNameTrl();
}


@GetMapping
("/getIdAsInt")
public int getIdAsInt(){
  return lookupvalue.getIdAsInt();
}


@GetMapping
("/fromObject")
public LookupValue fromObject(@RequestParam(name = "id") Object id,@RequestParam(name = "displayName") String displayName){
  return lookupvalue.fromObject(id,displayName);
}


@GetMapping
("/transform")
public T transform(@RequestParam(name = "transformation") Function<LookupValue,T> transformation){
  return lookupvalue.transform(transformation);
}


}