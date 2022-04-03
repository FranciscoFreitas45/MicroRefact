package com.ushahidi.swiftriver.core.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class FormController {

 private Form form;


@PutMapping
("/setAccount/{id}")
public void setAccount(@PathVariable(name = "id") Long id,@RequestParam(name = "account") Account account){
 jpaformdao.setAccount(id,account);
}


}