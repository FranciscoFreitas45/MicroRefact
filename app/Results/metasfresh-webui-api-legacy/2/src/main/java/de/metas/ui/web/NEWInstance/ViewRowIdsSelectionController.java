package de.metas.ui.web.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ViewRowIdsSelectionController {

 private ViewRowIdsSelection viewrowidsselection;


@GetMapping
("/of")
public ViewRowIdsSelection of(@RequestParam(name = "viewId") ViewId viewId,@RequestParam(name = "rowIds") DocumentIdsSelection rowIds){
  return viewrowidsselection.of(viewId,rowIds);
}


@GetMapping
("/ofNullableStrings")
public ViewRowIdsSelection ofNullableStrings(@RequestParam(name = "viewIdStr") String viewIdStr,@RequestParam(name = "rowIdsStringSet") Set<String> rowIdsStringSet){
  return viewrowidsselection.ofNullableStrings(viewIdStr,rowIdsStringSet);
}


@GetMapping
("/getRowIds")
public Object getRowIds(@RequestParam(name = "Object") Object Object){
  return viewrowidsselection.getRowIds(Object);
}


@GetMapping
("/getViewId")
public Object getViewId(@RequestParam(name = "Object") Object Object){
  return viewrowidsselection.getViewId(Object);
}


@GetMapping
("/isEmpty")
public boolean isEmpty(){
  return viewrowidsselection.isEmpty();
}


}