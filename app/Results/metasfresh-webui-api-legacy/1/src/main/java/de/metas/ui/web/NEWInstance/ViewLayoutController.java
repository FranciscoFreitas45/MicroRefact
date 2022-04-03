package de.metas.ui.web.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ViewLayoutController {

 private ViewLayout viewlayout;


@GetMapping
("/getFilters")
public ImmutableList<DocumentFilterDescriptor> getFilters(){
  return viewlayout.getFilters();
}


@GetMapping
("/getElements")
public List<DocumentLayoutElementDescriptor.Builder> getElements(){
  return viewlayout.getElements();
}


@GetMapping
("/get")
public Object get(@RequestParam(name = "Object") Object Object){
  return viewlayout.get(Object);
}


@GetMapping
("/getFields")
public Object getFields(@RequestParam(name = "Object") Object Object){
  return viewlayout.getFields(Object);
}


@GetMapping
("/getWidgetType")
public Object getWidgetType(@RequestParam(name = "Object") Object Object){
  return viewlayout.getWidgetType(Object);
}


@GetMapping
("/size")
public Object size(@RequestParam(name = "Object") Object Object){
  return viewlayout.size(Object);
}


@GetMapping
("/getCaption")
public String getCaption(@RequestParam(name = "adLanguage") String adLanguage){
  return viewlayout.getCaption(adLanguage);
}


@GetMapping
("/toBuilder")
public ChangeBuilder toBuilder(){
  return viewlayout.toBuilder();
}


@GetMapping
("/profileId")
public ChangeBuilder profileId(@RequestParam(name = "profileId") ViewProfileId profileId){
  return viewlayout.profileId(profileId);
}


@GetMapping
("/setWindowId")
public Builder setWindowId(@RequestParam(name = "windowId") WindowId windowId){
  return viewlayout.setWindowId(windowId);
}


@GetMapping
("/setCaption")
public Builder setCaption(@RequestParam(name = "caption") String caption){
  return viewlayout.setCaption(caption);
}


@GetMapping
("/withAllowNewRecordIfPresent")
public ViewLayout withAllowNewRecordIfPresent(@RequestParam(name = "allowNewCaption") Optional<String> allowNewCaption){
  return viewlayout.withAllowNewRecordIfPresent(allowNewCaption);
}


@GetMapping
("/setFilters")
public Builder setFilters(@RequestParam(name = "filters") Collection<DocumentFilterDescriptor> filters){
  return viewlayout.setFilters(filters);
}


@GetMapping
("/setIncludedViewLayout")
public Builder setIncludedViewLayout(@RequestParam(name = "includedViewLayout") IncludedViewLayout includedViewLayout){
  return viewlayout.setIncludedViewLayout(includedViewLayout);
}


@GetMapping
("/setHasTreeSupport")
public Builder setHasTreeSupport(@RequestParam(name = "hasTreeSupport") boolean hasTreeSupport){
  return viewlayout.setHasTreeSupport(hasTreeSupport);
}


@GetMapping
("/setTreeCollapsible")
public Builder setTreeCollapsible(@RequestParam(name = "treeCollapsible") boolean treeCollapsible){
  return viewlayout.setTreeCollapsible(treeCollapsible);
}


@GetMapping
("/setTreeExpandedDepth")
public Builder setTreeExpandedDepth(@RequestParam(name = "treeExpandedDepth") int treeExpandedDepth){
  return viewlayout.setTreeExpandedDepth(treeExpandedDepth);
}


@GetMapping
("/addElementsFromViewRowClass")
public Builder addElementsFromViewRowClass(@RequestParam(name = "viewRowClass") Class<T> viewRowClass,@RequestParam(name = "viewType") JSONViewDataType viewType){
  return viewlayout.addElementsFromViewRowClass(viewRowClass,viewType);
}


@GetMapping
("/setAllowOpeningRowDetails")
public Builder setAllowOpeningRowDetails(@RequestParam(name = "allowOpeningRowDetails") boolean allowOpeningRowDetails){
  return viewlayout.setAllowOpeningRowDetails(allowOpeningRowDetails);
}


@GetMapping
("/setEmptyResultText")
public Builder setEmptyResultText(@RequestParam(name = "emptyResultText") ITranslatableString emptyResultText){
  return viewlayout.setEmptyResultText(emptyResultText);
}


}