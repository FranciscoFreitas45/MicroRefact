package com.ushahidi.swiftriver.core.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.ushahidi.swiftriver.core.model.Media;
@RestController
@CrossOrigin
public class MediaDropController {

@Autowired
 private MediaDropService mediadropservice;


@GetMapping
("/Drop/{id}/Media/getImage")
public Media getImage(@PathVariable(name="id") long idBTYE){
return mediadropservice.getImage(idBTYE);
}


@PutMapping
("/Drop/{id}/Media/setImage")
public void setImage(@PathVariable(name="id") long idBTYE,@RequestBody Media image){
mediadropservice.setImage(idBTYE,image);
}


}