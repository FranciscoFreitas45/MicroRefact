package com.zis.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class StorageLacknessOpDTOController {

 private StorageLacknessOpDTO storagelacknessopdto;

 private StorageLacknessOpDTO storagelacknessopdto;


@GetMapping
("/isHasNext")
public boolean isHasNext(){
  return storagelacknessopdto.isHasNext();
}


}