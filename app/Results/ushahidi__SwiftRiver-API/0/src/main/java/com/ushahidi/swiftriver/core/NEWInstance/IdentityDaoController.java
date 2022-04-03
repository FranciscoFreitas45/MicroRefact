package com.ushahidi.swiftriver.core.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class IdentityDaoController {

 private IdentityDao identitydao;


@PutMapping
("/getIdentities")
public void getIdentities(@RequestParam(name = "drops") List<Drop> drops){
identitydao.getIdentities(drops);
}


}