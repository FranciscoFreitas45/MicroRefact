package org.live.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class MobileUserController {

 private MobileUserRepository mobileuserrepository;


@PutMapping
("/setAnchorFlag/{id}")
public void setAnchorFlag(@PathVariable(name = "id") String id,@RequestParam(name = "anchorFlag") boolean anchorFlag){
 mobileuserrepository.setAnchorFlag(id,anchorFlag);
}


}