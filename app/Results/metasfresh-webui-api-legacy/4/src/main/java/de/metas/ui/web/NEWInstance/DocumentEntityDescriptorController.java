package de.metas.ui.web.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class DocumentEntityDescriptorController {

 private DocumentEntityDescriptor documententitydescriptor;


@GetMapping
("/getFields")
public Map<String,DocumentFieldDescriptor> getFields(){
  return documententitydescriptor.getFields();
}


@GetMapping
("/isEmpty")
public Object isEmpty(@RequestParam(name = "Object") Object Object){
  return documententitydescriptor.isEmpty(Object);
}


@GetMapping
("/builder")
public Builder builder(){
  return documententitydescriptor.builder();
}


@GetMapping
("/isRefreshViewOnChangeEvents")
public boolean isRefreshViewOnChangeEvents(){
  return documententitydescriptor.isRefreshViewOnChangeEvents();
}


@GetMapping
("/isAllowQuickInput")
public boolean isAllowQuickInput(){
  return documententitydescriptor.isAllowQuickInput();
}


@GetMapping
("/hasField")
public boolean hasField(@RequestParam(name = "fieldName") String fieldName){
  return documententitydescriptor.hasField(fieldName);
}


}