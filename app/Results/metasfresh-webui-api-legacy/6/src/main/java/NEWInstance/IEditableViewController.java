package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class IEditableViewController {

 private IEditableView ieditableview;

 private IEditableView ieditableview;


@GetMapping
("/asEditableView")
public IEditableView asEditableView(@RequestParam(name = "view") IView view){
  return ieditableview.asEditableView(view);
}


@PutMapping
("/patchViewRow")
public void patchViewRow(@RequestParam(name = "ctx") RowEditingContext ctx,@RequestParam(name = "fieldChangeRequests") List<JSONDocumentChangedEvent> fieldChangeRequests){
ieditableview.patchViewRow(ctx,fieldChangeRequests);
}


}