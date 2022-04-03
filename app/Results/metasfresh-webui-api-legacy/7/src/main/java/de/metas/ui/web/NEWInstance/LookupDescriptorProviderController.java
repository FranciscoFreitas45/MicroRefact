package de.metas.ui.web.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class LookupDescriptorProviderController {

 private LookupDescriptorProvider lookupdescriptorprovider;


@GetMapping
("/provide")
public Optional<LookupDescriptor> provide(){
  return lookupdescriptorprovider.provide();
}


@GetMapping
("/provideForFilter")
public Optional<LookupDescriptor> provideForFilter(){
  return lookupdescriptorprovider.provideForFilter();
}


@GetMapping
("/getTableName")
public Optional<String> getTableName(){
  return lookupdescriptorprovider.getTableName();
}


@GetMapping
("/isNumericKey")
public boolean isNumericKey(){
  return lookupdescriptorprovider.isNumericKey();
}


@GetMapping
("/orElse")
public Object orElse(@RequestParam(name = "Object") Object Object){
  return lookupdescriptorprovider.orElse(Object);
}


}