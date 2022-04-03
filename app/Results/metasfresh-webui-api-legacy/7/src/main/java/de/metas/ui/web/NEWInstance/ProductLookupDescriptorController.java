package de.metas.ui.web.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ProductLookupDescriptorController {

 private ProductLookupDescriptor productlookupdescriptor;


@GetMapping
("/builderWithoutStockInfo")
public Object builderWithoutStockInfo(@RequestParam(name = "Object") Object Object){
  return productlookupdescriptor.builderWithoutStockInfo(Object);
}


@GetMapping
("/bpartnerParamName")
public Object bpartnerParamName(@RequestParam(name = "Object") Object Object){
  return productlookupdescriptor.bpartnerParamName(Object);
}


}