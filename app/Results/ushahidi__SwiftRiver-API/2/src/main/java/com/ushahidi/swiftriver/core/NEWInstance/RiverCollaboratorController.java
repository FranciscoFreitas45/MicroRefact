package com.ushahidi.swiftriver.core.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class RiverCollaboratorController {

 private JpaRiverDao jpariverdao;


@GetMapping
("/isActive/{id}")
public boolean isActive(@PathVariable(name = "id") Long id){
 return jpariverdao.isActive(id);
}


@PutMapping
("/setActive/{id}")
public void setActive(@PathVariable(name = "id") Long id,@RequestParam(name = "active") boolean active){
 jpariverdao.setActive(id,active);
}


@PutMapping
("/setReadOnly/{id}")
public void setReadOnly(@PathVariable(name = "id") Long id,@RequestParam(name = "readOnly") boolean readOnly){
 jpariverdao.setReadOnly(id,readOnly);
}


}