package com.ec.survey.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class KeyValueController {

 private KeyValue keyvalue;

 private KeyValue keyvalue;


@GetMapping
("/equals")
public boolean equals(@RequestParam(name = "obj") Object obj){
  return keyvalue.equals(obj);
}


@PutMapping
("/setValue")
public void setValue(@RequestParam(name = "value") String value){
keyvalue.setValue(value);
}


}