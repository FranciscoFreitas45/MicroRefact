package de.metas.ui.web.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class LookupValueByIdSupplierController {

 private LookupValueByIdSupplier lookupvaluebyidsupplier;


@GetMapping
("/findById")
public LookupValue findById(@RequestParam(name = "id") Object id){
  return lookupvaluebyidsupplier.findById(id);
}


}