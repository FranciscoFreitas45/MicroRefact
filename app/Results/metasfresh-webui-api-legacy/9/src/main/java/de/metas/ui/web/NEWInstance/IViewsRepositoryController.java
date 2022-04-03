package de.metas.ui.web.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class IViewsRepositoryController {

 private IViewsRepository iviewsrepository;


@GetMapping
("/getView")
public T getView(@RequestParam(name = "viewId") ViewId viewId,@RequestParam(name = "type") Class<T> type){
  return iviewsrepository.getView(viewId,type);
}


@PutMapping
("/closeView")
public void closeView(@RequestParam(name = "viewId") ViewId viewId,@RequestParam(name = "closeAction") ViewCloseAction closeAction){
iviewsrepository.closeView(viewId,closeAction);
}


@GetMapping
("/getViewIfExists")
public IView getViewIfExists(@RequestParam(name = "viewId") ViewId viewId){
  return iviewsrepository.getViewIfExists(viewId);
}


@GetMapping
("/createView")
public IView createView(@RequestParam(name = "request") CreateViewRequest request){
  return iviewsrepository.createView(request);
}


@GetMapping
("/getViewLayout")
public ViewLayout getViewLayout(@RequestParam(name = "windowId") WindowId windowId,@RequestParam(name = "viewDataType") JSONViewDataType viewDataType,@RequestParam(name = "profileId") ViewProfileId profileId){
  return iviewsrepository.getViewLayout(windowId,viewDataType,profileId);
}


@GetMapping
("/filterView")
public IView filterView(@RequestParam(name = "viewId") ViewId viewId,@RequestParam(name = "jsonRequest") JSONFilterViewRequest jsonRequest){
  return iviewsrepository.filterView(viewId,jsonRequest);
}


@GetMapping
("/getFilterParameterTypeahead")
public Object getFilterParameterTypeahead(@RequestParam(name = "Object") Object Object){
  return iviewsrepository.getFilterParameterTypeahead(Object);
}


@GetMapping
("/getFilterParameterDropdown")
public Object getFilterParameterDropdown(@RequestParam(name = "Object") Object Object){
  return iviewsrepository.getFilterParameterDropdown(Object);
}


@PutMapping
("/invalidateView")
public void invalidateView(@RequestParam(name = "view") IView view){
iviewsrepository.invalidateView(view);
}


}