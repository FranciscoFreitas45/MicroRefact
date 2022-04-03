package de.metas.ui.web.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class MaterialCockpitFiltersController {

 private MaterialCockpitFilters materialcockpitfilters;


@GetMapping
("/getFilterDescriptors")
public DocumentFilterDescriptorsProvider getFilterDescriptors(){
  return materialcockpitfilters.getFilterDescriptors();
}


@GetMapping
("/extractDocumentFilters")
public DocumentFilterList extractDocumentFilters(@RequestParam(name = "request") CreateViewRequest request){
  return materialcockpitfilters.extractDocumentFilters(request);
}


@GetMapping
("/createAutoFilters")
public DocumentFilterList createAutoFilters(){
  return materialcockpitfilters.createAutoFilters();
}


@GetMapping
("/getFilterByDate")
public LocalDate getFilterByDate(@RequestParam(name = "filters") DocumentFilterList filters){
  return materialcockpitfilters.getFilterByDate(filters);
}


@GetMapping
("/createQuery")
public IQuery<I_MD_Cockpit> createQuery(@RequestParam(name = "filters") DocumentFilterList filters){
  return materialcockpitfilters.createQuery(filters);
}


@GetMapping
("/list")
public Object list(@RequestParam(name = "Object") Object Object){
  return materialcockpitfilters.list(Object);
}


}