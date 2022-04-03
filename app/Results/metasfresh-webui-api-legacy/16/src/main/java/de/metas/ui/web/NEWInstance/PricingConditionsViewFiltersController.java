package de.metas.ui.web.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class PricingConditionsViewFiltersController {

 private PricingConditionsViewFilters pricingconditionsviewfilters;


@GetMapping
("/getFilterDescriptors")
public Collection<DocumentFilterDescriptor> getFilterDescriptors(){
  return pricingconditionsviewfilters.getFilterDescriptors();
}


@GetMapping
("/getFilterDescriptorsProvider")
public DocumentFilterDescriptorsProvider getFilterDescriptorsProvider(){
  return pricingconditionsviewfilters.getFilterDescriptorsProvider();
}


@GetMapping
("/extractFilters")
public DocumentFilterList extractFilters(@RequestParam(name = "request") CreateViewRequest request){
  return pricingconditionsviewfilters.extractFilters(request);
}


}