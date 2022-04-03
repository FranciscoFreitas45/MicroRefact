package de.metas.ui.web.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class PurchaseViewLayoutFactoryController {

 private PurchaseViewLayoutFactory purchaseviewlayoutfactory;


@GetMapping
("/builder")
public Object builder(@RequestParam(name = "Object") Object Object){
  return purchaseviewlayoutfactory.builder(Object);
}


@GetMapping
("/caption")
public Object caption(@RequestParam(name = "Object") Object Object){
  return purchaseviewlayoutfactory.caption(Object);
}


@GetMapping
("/getViewLayout")
public ViewLayout getViewLayout(@RequestParam(name = "windowId") WindowId windowId,@RequestParam(name = "viewDataType") JSONViewDataType viewDataType){
  return purchaseviewlayoutfactory.getViewLayout(windowId,viewDataType);
}


}