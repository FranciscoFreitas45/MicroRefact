package com.ushahidi.swiftriver.core.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.ushahidi.swiftriver.core.model.Form;
@RestController
@CrossOrigin
public class FormAccountController {

@Autowired
 private FormAccountService formaccountservice;


@PutMapping
("/Account/{id}/Form/setForms")
public void setForms(@PathVariable(name="id") long id,@RequestBody List<Form> forms){
formaccountservice.setForms(id,forms);
}


@GetMapping
("/Account/{id}/Form/getForms")
public List<Form> getForms(@PathVariable(name="id") long id){
return formaccountservice.getForms(id);
}


}