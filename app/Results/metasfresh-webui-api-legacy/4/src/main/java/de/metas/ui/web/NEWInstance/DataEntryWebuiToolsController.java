package de.metas.ui.web.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class DataEntryWebuiToolsController {

 private DataEntryWebuiTools dataentrywebuitools;


@GetMapping
("/computeFieldName")
public String computeFieldName(@RequestParam(name = "dataEntryFieldId") DataEntryFieldId dataEntryFieldId){
  return dataentrywebuitools.computeFieldName(dataEntryFieldId);
}


}