package org.gliderwiki.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class WeAlarmInfoController {

 private WeAlarmInfo wealarminfo;

 private WeAlarmInfo wealarminfo;


@PutMapping
("/setWe_alarm_type")
public void setWe_alarm_type(@RequestParam(name = "we_alarm_type") String we_alarm_type){
wealarminfo.setWe_alarm_type(we_alarm_type);
}


@PutMapping
("/setWe_user_idx")
public void setWe_user_idx(@RequestParam(name = "we_user_idx") Integer we_user_idx){
wealarminfo.setWe_user_idx(we_user_idx);
}


@PutMapping
("/setWe_target_user_idx")
public void setWe_target_user_idx(@RequestParam(name = "we_target_user_idx") Integer we_target_user_idx){
wealarminfo.setWe_target_user_idx(we_target_user_idx);
}


@PutMapping
("/setWe_alarm_text")
public void setWe_alarm_text(@RequestParam(name = "we_alarm_text") String we_alarm_text){
wealarminfo.setWe_alarm_text(we_alarm_text);
}


@PutMapping
("/setWe_use_yn")
public void setWe_use_yn(@RequestParam(name = "we_use_yn") String we_use_yn){
wealarminfo.setWe_use_yn(we_use_yn);
}


@PutMapping
("/setWe_ins_date")
public void setWe_ins_date(@RequestParam(name = "we_ins_date") String we_ins_date){
wealarminfo.setWe_ins_date(we_ins_date);
}


@PutMapping
("/setWe_ins_user")
public void setWe_ins_user(@RequestParam(name = "we_ins_user") Integer we_ins_user){
wealarminfo.setWe_ins_user(we_ins_user);
}


@PutMapping
("/setWe_space_idx")
public void setWe_space_idx(@RequestParam(name = "we_space_idx") Integer we_space_idx){
wealarminfo.setWe_space_idx(we_space_idx);
}


@PutMapping
("/setWe_wiki_idx")
public void setWe_wiki_idx(@RequestParam(name = "we_wiki_idx") Integer we_wiki_idx){
wealarminfo.setWe_wiki_idx(we_wiki_idx);
}


}