package de.metas.ui.web.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ViewActionDescriptorsListController {

 private ViewActionDescriptorsList viewactiondescriptorslist;


@GetMapping
("/mergeWith")
public ViewActionDescriptorsList mergeWith(@RequestParam(name = "actionsToAdd") ViewActionDescriptorsList actionsToAdd){
  return viewactiondescriptorslist.mergeWith(actionsToAdd);
}


}