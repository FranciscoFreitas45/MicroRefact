package org.gliderwiki.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class TempUploadVoController {

 private TempUploadVo tempuploadvo;

 private TempUploadVo tempuploadvo;


@GetMapping
("/isUploaded")
public boolean isUploaded(){
  return tempuploadvo.isUploaded();
}


}