package de.metas.ui.web.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class JSONLookupValueController {

 private JSONLookupValue jsonlookupvalue;


@GetMapping
("/getKeyAsInt")
public int getKeyAsInt(){
  return jsonlookupvalue.getKeyAsInt();
}


@GetMapping
("/getKey")
public Object getKey(@RequestParam(name = "Object") Object Object){
  return jsonlookupvalue.getKey(Object);
}


@GetMapping
("/stringLookupValueFromJsonMap")
public StringLookupValue stringLookupValueFromJsonMap(@RequestParam(name = "map") Map<String,Object> map){
  return jsonlookupvalue.stringLookupValueFromJsonMap(map);
}


@GetMapping
("/unknown")
public JSONLookupValue unknown(@RequestParam(name = "id") int id){
  return jsonlookupvalue.unknown(id);
}


}