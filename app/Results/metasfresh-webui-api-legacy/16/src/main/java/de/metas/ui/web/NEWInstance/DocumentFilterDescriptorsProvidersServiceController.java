package de.metas.ui.web.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class DocumentFilterDescriptorsProvidersServiceController {

 private DocumentFilterDescriptorsProvidersService documentfilterdescriptorsprovidersservice;


@GetMapping
("/createFiltersProvider")
public DocumentFilterDescriptorsProvider createFiltersProvider(@RequestParam(name = "adTabId") AdTabId adTabId,@RequestParam(name = "tableName") String tableName,@RequestParam(name = "fields") Collection<DocumentFieldDescriptor> fields){
  return documentfilterdescriptorsprovidersservice.createFiltersProvider(adTabId,tableName,fields);
}


}