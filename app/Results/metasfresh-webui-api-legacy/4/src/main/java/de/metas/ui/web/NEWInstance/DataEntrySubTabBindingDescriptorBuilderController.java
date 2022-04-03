package de.metas.ui.web.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class DataEntrySubTabBindingDescriptorBuilderController {

 private DataEntrySubTabBindingDescriptorBuilder dataentrysubtabbindingdescriptorbuilder;


@GetMapping
("/getDataEntryWebuiTools")
public Object getDataEntryWebuiTools(@RequestParam(name = "Object") Object Object){
  return dataentrysubtabbindingdescriptorbuilder.getDataEntryWebuiTools(Object);
}


}