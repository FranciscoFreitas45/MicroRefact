package de.metas.ui.web.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class LookupDescriptorController {

 private LookupDescriptor lookupdescriptor;


@GetMapping
("/isNumericKey")
public boolean isNumericKey(){
  return lookupdescriptor.isNumericKey();
}


@GetMapping
("/castOrNull")
public T castOrNull(@RequestParam(name = "lookupDescriptorClass") Class<T> lookupDescriptorClass){
  return lookupdescriptor.castOrNull(lookupDescriptorClass);
}


}