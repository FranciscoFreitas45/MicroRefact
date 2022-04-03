package com.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class JSONObjectController {

 private JSONObject jsonobject;

 private JSONObject jsonobject;


@GetMapping
("/fromObject")
public JSONObject fromObject(@RequestParam(name = "object") Object object,@RequestParam(name = "jsonConfig") JsonConfig jsonConfig){
  return jsonobject.fromObject(object,jsonConfig);
}


@GetMapping
("/toString")
public String toString(@RequestParam(name = "indentFactor") int indentFactor,@RequestParam(name = "indent") int indent){
  return jsonobject.toString(indentFactor,indent);
}


@GetMapping
("/has")
public boolean has(@RequestParam(name = "key") String key){
  return jsonobject.has(key);
}


@GetMapping
("/element")
public JSONObject element(@RequestParam(name = "key") String key,@RequestParam(name = "value") Object value,@RequestParam(name = "jsonConfig") JsonConfig jsonConfig){
  return jsonobject.element(key,value,jsonConfig);
}


@GetMapping
("/accumulate")
public JSONObject accumulate(@RequestParam(name = "key") String key,@RequestParam(name = "value") Object value,@RequestParam(name = "jsonConfig") JsonConfig jsonConfig){
  return jsonobject.accumulate(key,value,jsonConfig);
}


@GetMapping
("/isNullObject")
public boolean isNullObject(){
  return jsonobject.isNullObject();
}


@GetMapping
("/isEmpty")
public boolean isEmpty(){
  return jsonobject.isEmpty();
}


@GetMapping
("/names")
public JSONArray names(@RequestParam(name = "jsonConfig") JsonConfig jsonConfig){
  return jsonobject.names(jsonConfig);
}


@GetMapping
("/opt")
public Object opt(@RequestParam(name = "key") String key){
  return jsonobject.opt(key);
}


@GetMapping
("/remove")
public Object remove(@RequestParam(name = "key") String key){
  return jsonobject.remove(key);
}


@GetMapping
("/entrySet")
public Set entrySet(){
  return jsonobject.entrySet();
}


@GetMapping
("/size")
public int size(){
  return jsonobject.size();
}


}