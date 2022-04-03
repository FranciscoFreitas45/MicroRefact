package org.gliderwiki.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class WeInstallUserController {

 private WeInstallUser weinstalluser;

 private WeInstallUser weinstalluser;


@PutMapping
("/setWe_install_date")
public void setWe_install_date(@RequestParam(name = "we_install_date") Date we_install_date){
weinstalluser.setWe_install_date(we_install_date);
}


@PutMapping
("/setWe_active_key")
public void setWe_active_key(@RequestParam(name = "we_active_key") String we_active_key){
weinstalluser.setWe_active_key(we_active_key);
}


@PutMapping
("/setWe_auth_date")
public void setWe_auth_date(@RequestParam(name = "we_auth_date") Date we_auth_date){
weinstalluser.setWe_auth_date(we_auth_date);
}


@PutMapping
("/setWe_auth_yn")
public void setWe_auth_yn(@RequestParam(name = "we_auth_yn") String we_auth_yn){
weinstalluser.setWe_auth_yn(we_auth_yn);
}


@PutMapping
("/setWe_domain")
public void setWe_domain(@RequestParam(name = "we_domain") String we_domain){
weinstalluser.setWe_domain(we_domain);
}


@PutMapping
("/setWe_email")
public void setWe_email(@RequestParam(name = "we_email") String we_email){
weinstalluser.setWe_email(we_email);
}


}