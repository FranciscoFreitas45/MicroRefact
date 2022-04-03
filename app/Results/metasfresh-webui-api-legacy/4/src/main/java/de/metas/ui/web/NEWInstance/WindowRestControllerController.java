package de.metas.ui.web.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class WindowRestControllerController {

 private WindowRestController windowrestcontroller;


@GetMapping
("/getDocumentFieldZoomInto")
public DocumentZoomIntoInfo getDocumentFieldZoomInto(@RequestParam(name = "document") Document document,@RequestParam(name = "fieldName") String fieldName){
  return windowrestcontroller.getDocumentFieldZoomInto(document,fieldName);
}


}