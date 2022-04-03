package de.metas.ui.web.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class DocumentFilterDescriptorsProviderController {

 private DocumentFilterDescriptorsProvider documentfilterdescriptorsprovider;


@GetMapping
("/getByFilterId")
public DocumentFilterDescriptor getByFilterId(@RequestParam(name = "filterId") String filterId){
  return documentfilterdescriptorsprovider.getByFilterId(filterId);
}


}