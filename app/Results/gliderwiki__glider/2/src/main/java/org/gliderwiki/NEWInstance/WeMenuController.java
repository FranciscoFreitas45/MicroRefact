package org.gliderwiki.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class WeMenuController {

 private WeMenu wemenu;

 private WeMenu wemenu;


@PutMapping
("/setWe_use_yn")
public void setWe_use_yn(@RequestParam(name = "we_use_yn") String we_use_yn){
wemenu.setWe_use_yn(we_use_yn);
}


@PutMapping
("/setWe_menu_depth")
public void setWe_menu_depth(@RequestParam(name = "we_menu_depth") Integer we_menu_depth){
wemenu.setWe_menu_depth(we_menu_depth);
}


@PutMapping
("/setWe_menu_type")
public void setWe_menu_type(@RequestParam(name = "we_menu_type") String we_menu_type){
wemenu.setWe_menu_type(we_menu_type);
}


}