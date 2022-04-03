package de.metas.ui.web.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ViewProfileIdController {

 private ViewProfileId viewprofileid;


@GetMapping
("/isNull")
public boolean isNull(@RequestParam(name = "profileId") ViewProfileId profileId){
  return viewprofileid.isNull(profileId);
}


}