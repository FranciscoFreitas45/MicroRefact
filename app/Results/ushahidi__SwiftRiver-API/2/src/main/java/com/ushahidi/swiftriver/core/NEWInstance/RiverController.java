package com.ushahidi.swiftriver.core.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class RiverController {

 private JpaRiverDao jpariverdao;


@PutMapping
("/setDropCount/{id}")
public void setDropCount(@PathVariable(name = "id") Long id,@RequestParam(name = "dropCount") Integer dropCount){
 jpariverdao.setDropCount(id,dropCount);
}


@PutMapping
("/setAccount/{id}")
public void setAccount(@PathVariable(name = "id") Long id,@RequestParam(name = "account") Account account){
 jpariverdao.setAccount(id,account);
}


@PutMapping
("/setActive/{id}")
public void setActive(@PathVariable(name = "id") Long id,@RequestParam(name = "active") Boolean active){
 jpariverdao.setActive(id,active);
}


@PutMapping
("/setDropQuota/{id}")
public void setDropQuota(@PathVariable(name = "id") Long id,@RequestParam(name = "dropQuota") Integer dropQuota){
 jpariverdao.setDropQuota(id,dropQuota);
}


@GetMapping
("/equals/{id}")
public boolean equals(@PathVariable(name = "id") Long id,@RequestParam(name = "obj") Object obj){
 return jpariverdao.equals(id,obj);
}


}