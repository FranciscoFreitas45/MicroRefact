package org.gliderwiki.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class WeUserController {

 private WeUser weuser;

 private WeUser weuser;


@PutMapping
("/setWe_user_idx")
public void setWe_user_idx(@RequestParam(name = "we_user_idx") Integer we_user_idx){
weuser.setWe_user_idx(we_user_idx);
}


@PutMapping
("/setWe_user_auth_yn")
public void setWe_user_auth_yn(@RequestParam(name = "we_user_auth_yn") String we_user_auth_yn){
weuser.setWe_user_auth_yn(we_user_auth_yn);
}


@PutMapping
("/setWe_user_name")
public void setWe_user_name(@RequestParam(name = "we_user_name") String we_user_name){
weuser.setWe_user_name(we_user_name);
}


@PutMapping
("/setWe_user_id")
public void setWe_user_id(@RequestParam(name = "we_user_id") String we_user_id){
weuser.setWe_user_id(we_user_id);
}


@PutMapping
("/setWe_user_nick")
public void setWe_user_nick(@RequestParam(name = "we_user_nick") String we_user_nick){
weuser.setWe_user_nick(we_user_nick);
}


@PutMapping
("/setWe_user_pwd")
public void setWe_user_pwd(@RequestParam(name = "we_user_pwd") String we_user_pwd){
weuser.setWe_user_pwd(we_user_pwd);
}


@PutMapping
("/setWe_user_auth")
public void setWe_user_auth(@RequestParam(name = "we_user_auth") String we_user_auth){
weuser.setWe_user_auth(we_user_auth);
}


@PutMapping
("/setWe_user_key")
public void setWe_user_key(@RequestParam(name = "we_user_key") String we_user_key){
weuser.setWe_user_key(we_user_key);
}


@PutMapping
("/setWe_user_join_date")
public void setWe_user_join_date(@RequestParam(name = "we_user_join_date") Date we_user_join_date){
weuser.setWe_user_join_date(we_user_join_date);
}


}