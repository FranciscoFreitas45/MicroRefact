package de.metas.ui.web.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class HUEditorRowAttributesProviderController {

 private HUEditorRowAttributesProvider hueditorrowattributesprovider;


@PutMapping
("/invalidateAll")
public void invalidateAll(){
hueditorrowattributesprovider.invalidateAll();
}


@GetMapping
("/createAttributeKey")
public DocumentId createAttributeKey(@RequestParam(name = "huId") HuId huId){
  return hueditorrowattributesprovider.createAttributeKey(huId);
}


@GetMapping
("/getAttributes")
public HUEditorRowAttributes getAttributes(@RequestParam(name = "viewRowId") DocumentId viewRowId,@RequestParam(name = "huId") DocumentId huId){
  return hueditorrowattributesprovider.getAttributes(viewRowId,huId);
}


}