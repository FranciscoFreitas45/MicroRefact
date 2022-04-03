package org.gliderwiki.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class WeTemplateController {

 private WeTemplate wetemplate;

 private WeTemplate wetemplate;


@PutMapping
("/setWe_use_yn")
public void setWe_use_yn(@RequestParam(name = "we_use_yn") String we_use_yn){
wetemplate.setWe_use_yn(we_use_yn);
}


}