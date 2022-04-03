package org.jugbd.mnet.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.jugbd.mnet.domain.PictureInformation;
@RestController
@CrossOrigin
public class PictureInformationRegisterController {

@Autowired
 private PictureInformationRegisterService pictureinformationregisterservice;


@PutMapping
("/Register/{id}/PictureInformation/setPictureInformation")
public void setPictureInformation(@PathVariable(name="id") Long id,@RequestBody PictureInformation pictureInformation){
pictureinformationregisterservice.setPictureInformation(id,pictureInformation);
}


@GetMapping
("/Register/{id}/PictureInformation/getPictureInformation")
public PictureInformation getPictureInformation(@PathVariable(name="id") Long id){
return pictureinformationregisterservice.getPictureInformation(id);
}


}