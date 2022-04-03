package org.jugbd.mnet.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.jugbd.mnet.domain.PictureInformation;
@RestController
@CrossOrigin
public class PictureInformationOutdoorRegisterController {

@Autowired
 private PictureInformationOutdoorRegisterService pictureinformationoutdoorregisterservice;


@GetMapping
("/OutdoorRegister/{id}/PictureInformation/getPictureInformation")
public PictureInformation getPictureInformation(@PathVariable(name="id") Long id){
return pictureinformationoutdoorregisterservice.getPictureInformation(id);
}


@GetMapping
("/OutdoorRegister/{id}/PictureInformation/setPictureInformation")
public OutdoorRegister setPictureInformation(@PathVariable(name="id") Long id,@RequestParam PictureInformation pictureInformation){
return pictureinformationoutdoorregisterservice.setPictureInformation(id,pictureInformation);
}


}