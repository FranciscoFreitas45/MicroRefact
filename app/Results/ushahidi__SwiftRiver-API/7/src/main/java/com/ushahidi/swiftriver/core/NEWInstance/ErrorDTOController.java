package com.ushahidi.swiftriver.core.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ErrorDTOController {

 private ErrorDTO errordto;

 private ErrorDTO errordto;


@PutMapping
("/setErrors")
public void setErrors(@RequestParam(name = "errors") List<ErrorField> errors){
errordto.setErrors(errors);
}


}