package de.metas.ui.web.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class QuickInputDescriptorFactoryServiceController {

 private QuickInputDescriptorFactoryService quickinputdescriptorfactoryservice;


@GetMapping
("/hasQuickInputEntityDescriptor")
public boolean hasQuickInputEntityDescriptor(@RequestParam(name = "key") QuickInputDescriptorKey key){
  return quickinputdescriptorfactoryservice.hasQuickInputEntityDescriptor(key);
}


}