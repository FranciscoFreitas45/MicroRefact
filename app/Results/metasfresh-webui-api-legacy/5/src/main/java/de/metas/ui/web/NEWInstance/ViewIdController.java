package de.metas.ui.web.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ViewIdController {

 private ViewId viewid;


@GetMapping
("/withWindowId")
public ViewId withWindowId(@RequestParam(name = "newWindowId") WindowId newWindowId){
  return viewid.withWindowId(newWindowId);
}


@GetMapping
("/random")
public ViewId random(@RequestParam(name = "windowId") WindowId windowId){
  return viewid.random(windowId);
}


@GetMapping
("/getWindowId")
public WindowId getWindowId(){
  return viewid.getWindowId();
}


@GetMapping
("/of")
public ViewId of(@RequestParam(name = "windowIdStr") String windowIdStr,@RequestParam(name = "viewIdStr") String viewIdStr){
  return viewid.of(windowIdStr,viewIdStr);
}


@PutMapping
("/assertWindowId")
public void assertWindowId(@RequestParam(name = "expectedWindowId") WindowId expectedWindowId){
viewid.assertWindowId(expectedWindowId);
}


@GetMapping
("/toJson")
public String toJson(){
  return viewid.toJson();
}


@GetMapping
("/ofViewIdString")
public ViewId ofViewIdString(@RequestParam(name = "viewIdStr") String viewIdStr,@RequestParam(name = "expectedWindowId") WindowId expectedWindowId){
  return viewid.ofViewIdString(viewIdStr,expectedWindowId);
}


@GetMapping
("/ofParts")
public ViewId ofParts(@RequestParam(name = "windowId") WindowId windowId,@RequestParam(name = "viewIdPart") String viewIdPart,@RequestParam(name = "otherParts") String otherParts){
  return viewid.ofParts(windowId,viewIdPart,otherParts);
}


}