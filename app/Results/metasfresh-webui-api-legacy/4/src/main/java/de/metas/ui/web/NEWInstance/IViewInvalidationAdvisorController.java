package de.metas.ui.web.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class IViewInvalidationAdvisorController {

 private IViewInvalidationAdvisor iviewinvalidationadvisor;


@GetMapping
("/findAffectedRowIds")
public Set<DocumentId> findAffectedRowIds(@RequestParam(name = "recordRefs") TableRecordReferenceSet recordRefs,@RequestParam(name = "view") IView view){
  return iviewinvalidationadvisor.findAffectedRowIds(recordRefs,view);
}


}