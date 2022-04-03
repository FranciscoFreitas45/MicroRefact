package org.sdrc.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ValueObjectController {

 private ValueObject valueobject;


@PutMapping
("/setKey")
public void setKey(@RequestParam(name = "key") String key){
valueobject.setKey(key);
}


@PutMapping
("/setValue")
public void setValue(@RequestParam(name = "value") Object value){
valueobject.setValue(value);
}


@PutMapping
("/setMetadata")
public void setMetadata(@RequestParam(name = "metadata") String metadata){
valueobject.setMetadata(metadata);
}


@PutMapping
("/setDescription")
public void setDescription(@RequestParam(name = "description") String description){
valueobject.setDescription(description);
}


@PutMapping
("/setNid")
public void setNid(@RequestParam(name = "nid") Integer nid){
valueobject.setNid(nid);
}


}