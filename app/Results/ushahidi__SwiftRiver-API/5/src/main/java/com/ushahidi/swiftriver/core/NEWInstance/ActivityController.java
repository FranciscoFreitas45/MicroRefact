package com.ushahidi.swiftriver.core.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ActivityController {

 private JpaActivityDao jpaactivitydao;


@PutMapping
("/setActionDateAdd/{id}")
public void setActionDateAdd(@PathVariable(name = "id") long id,@RequestParam(name = "actionDateAdd") Date actionDateAdd){
 jpaactivitydao.setActionDateAdd(id,actionDateAdd);
}


}