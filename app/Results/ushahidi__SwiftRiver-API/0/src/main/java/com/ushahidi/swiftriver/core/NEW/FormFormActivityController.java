package com.ushahidi.swiftriver.core.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.ushahidi.swiftriver.core.model.Form;
@RestController
@CrossOrigin
public class FormFormActivityController {

@Autowired
 private FormFormActivityService formformactivityservice;


@GetMapping
("/FormActivity/{id}/Form/getActionOnObj")
public Form getActionOnObj(@PathVariable(name="id") Long idVMYL){
return formformactivityservice.getActionOnObj(idVMYL);
}


@PutMapping
("/FormActivity/{id}/Form/setActionOnObj")
public void setActionOnObj(@PathVariable(name="id") Long idVMYL,@RequestBody Form actionOnObj){
formformactivityservice.setActionOnObj(idVMYL,actionOnObj);
}


}