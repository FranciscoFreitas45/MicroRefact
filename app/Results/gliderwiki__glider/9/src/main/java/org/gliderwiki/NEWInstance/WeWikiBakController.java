package org.gliderwiki.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class WeWikiBakController {

 private WeWikiBak wewikibak;

 private WeWikiBak wewikibak;


@PutMapping
("/setWe_mov_date")
public void setWe_mov_date(@RequestParam(name = "we_mov_date") Date we_mov_date){
wewikibak.setWe_mov_date(we_mov_date);
}


@PutMapping
("/setWe_upd_date")
public void setWe_upd_date(@RequestParam(name = "we_upd_date") Date we_upd_date){
wewikibak.setWe_upd_date(we_upd_date);
}


@PutMapping
("/setWe_upd_user")
public void setWe_upd_user(@RequestParam(name = "we_upd_user") Integer we_upd_user){
wewikibak.setWe_upd_user(we_upd_user);
}


@GetMapping
("/toString")
public String toString(){
  return wewikibak.toString();
}


}