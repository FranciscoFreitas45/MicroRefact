package de.metas.ui.web.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class CreateViewRequestController {

 private CreateViewRequest createviewrequest;


@GetMapping
("/isUseAutoFilters")
public boolean isUseAutoFilters(){
  return createviewrequest.isUseAutoFilters();
}


@GetMapping
("/filterViewBuilder")
public Builder filterViewBuilder(@RequestParam(name = "view") IView view){
  return createviewrequest.filterViewBuilder(view);
}


@GetMapping
("/deleteStickyFilterBuilder")
public Builder deleteStickyFilterBuilder(@RequestParam(name = "view") IView view,@RequestParam(name = "stickyFilterIdToDelete") String stickyFilterIdToDelete){
  return createviewrequest.deleteStickyFilterBuilder(view,stickyFilterIdToDelete);
}


@GetMapping
("/setFilters")
public Builder setFilters(@RequestParam(name = "filters") DocumentFilterList filters){
  return createviewrequest.setFilters(filters);
}


@GetMapping
("/isApplySecurityRestrictions")
public boolean isApplySecurityRestrictions(){
  return createviewrequest.isApplySecurityRestrictions();
}


@GetMapping
("/builder")
public Builder builder(@RequestParam(name = "viewId") ViewId viewId,@RequestParam(name = "viewType") JSONViewDataType viewType){
  return createviewrequest.builder(viewId,viewType);
}


@GetMapping
("/setParentViewId")
public Builder setParentViewId(@RequestParam(name = "parentViewId") ViewId parentViewId){
  return createviewrequest.setParentViewId(parentViewId);
}


@GetMapping
("/setParentRowId")
public Builder setParentRowId(@RequestParam(name = "parentRowId") DocumentId parentRowId){
  return createviewrequest.setParentRowId(parentRowId);
}


@GetMapping
("/setParameter")
public Builder setParameter(@RequestParam(name = "name") String name,@RequestParam(name = "value") Object value){
  return createviewrequest.setParameter(name,value);
}


@GetMapping
("/addStickyFilters")
public Builder addStickyFilters(@RequestParam(name = "stickyFilter") DocumentFilter stickyFilter){
  return createviewrequest.addStickyFilters(stickyFilter);
}


}