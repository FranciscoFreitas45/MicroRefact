package de.metas.ui.web.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class IViewDataRepositoryController {

 private IViewDataRepository iviewdatarepository;


@GetMapping
("/getViewFilterDescriptors")
public DocumentFilterDescriptorsProvider getViewFilterDescriptors(){
  return iviewdatarepository.getViewFilterDescriptors();
}


}