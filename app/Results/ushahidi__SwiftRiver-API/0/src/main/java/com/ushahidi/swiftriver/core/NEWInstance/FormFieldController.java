package com.ushahidi.swiftriver.core.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class FormFieldController {

 private FormField formfield;


@PutMapping
("/setForm/{id}")
public void setForm(@PathVariable(name = "id") Long id,@RequestParam(name = "form") Form form){
 jpaformdao.setForm(id,form);
}


}