package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class HUEditorRowIdController {

 private HUEditorRowId hueditorrowid;

 private HUEditorRowId hueditorrowid;


@GetMapping
("/toDocumentId")
public DocumentId toDocumentId(){
  return hueditorrowid.toDocumentId();
}


@GetMapping
("/ofHUStorage")
public HUEditorRowId ofHUStorage(@RequestParam(name = "huId") HuId huId,@RequestParam(name = "topLevelHUId") HuId topLevelHUId,@RequestParam(name = "storageProductId") ProductId storageProductId){
  return hueditorrowid.ofHUStorage(huId,topLevelHUId,storageProductId);
}


}