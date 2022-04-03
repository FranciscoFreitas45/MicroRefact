package de.metas.ui.web.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class SqlViewFactoryController {

 private SqlViewFactory sqlviewfactory;


@PutMapping
("/setDefaultProfileId")
public void setDefaultProfileId(@RequestParam(name = "windowId") WindowId windowId,@RequestParam(name = "profileId") ViewProfileId profileId){
sqlviewfactory.setDefaultProfileId(windowId,profileId);
}


}