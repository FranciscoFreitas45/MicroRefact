package com.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class JSONArrayController {

 private JSONArray jsonarray;

 private JSONArray jsonarray;


@GetMapping
("/toString")
public String toString(@RequestParam(name = "indentFactor") int indentFactor,@RequestParam(name = "indent") int indent){
  return jsonarray.toString(indentFactor,indent);
}


@GetMapping
("/size")
public int size(){
  return jsonarray.size();
}


@GetMapping
("/isExpandElements")
public boolean isExpandElements(){
  return jsonarray.isExpandElements();
}


@GetMapping
("/join")
public String join(@RequestParam(name = "separator") String separator,@RequestParam(name = "stripQuotes") boolean stripQuotes){
  return jsonarray.join(separator,stripQuotes);
}


@GetMapping
("/element")
public JSONArray element(@RequestParam(name = "value") String value,@RequestParam(name = "jsonConfig") JsonConfig jsonConfig){
  return jsonarray.element(value,jsonConfig);
}


}