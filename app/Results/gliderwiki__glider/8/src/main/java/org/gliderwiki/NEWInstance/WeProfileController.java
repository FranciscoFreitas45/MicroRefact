package org.gliderwiki.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class WeProfileController {

 private WeProfile weprofile;

 private WeProfile weprofile;


@PutMapping
("/setWe_point")
public void setWe_point(@RequestParam(name = "we_point") Integer we_point){
weprofile.setWe_point(we_point);
}


@PutMapping
("/setWe_tech_yn")
public void setWe_tech_yn(@RequestParam(name = "we_tech_yn") String we_tech_yn){
weprofile.setWe_tech_yn(we_tech_yn);
}


@PutMapping
("/setWe_away_yn")
public void setWe_away_yn(@RequestParam(name = "we_away_yn") String we_away_yn){
weprofile.setWe_away_yn(we_away_yn);
}


@PutMapping
("/setWe_upd_date")
public void setWe_upd_date(@RequestParam(name = "we_upd_date") Date we_upd_date){
weprofile.setWe_upd_date(we_upd_date);
}


@PutMapping
("/setWe_file_save_name")
public void setWe_file_save_name(@RequestParam(name = "we_file_save_name") String we_file_save_name){
weprofile.setWe_file_save_name(we_file_save_name);
}


@PutMapping
("/setWe_file_save_path")
public void setWe_file_save_path(@RequestParam(name = "we_file_save_path") String we_file_save_path){
weprofile.setWe_file_save_path(we_file_save_path);
}


@PutMapping
("/setWe_thumb_name")
public void setWe_thumb_name(@RequestParam(name = "we_thumb_name") String we_thumb_name){
weprofile.setWe_thumb_name(we_thumb_name);
}


@PutMapping
("/setWe_thumb_path")
public void setWe_thumb_path(@RequestParam(name = "we_thumb_path") String we_thumb_path){
weprofile.setWe_thumb_path(we_thumb_path);
}


@PutMapping
("/setWe_user_site")
public void setWe_user_site(@RequestParam(name = "we_user_site") String we_user_site){
weprofile.setWe_user_site(we_user_site);
}


@PutMapping
("/setWe_user_email")
public void setWe_user_email(@RequestParam(name = "we_user_email") String we_user_email){
weprofile.setWe_user_email(we_user_email);
}


@PutMapping
("/setWe_ins_date")
public void setWe_ins_date(@RequestParam(name = "we_ins_date") Date we_ins_date){
weprofile.setWe_ins_date(we_ins_date);
}


@PutMapping
("/setWe_grade")
public void setWe_grade(@RequestParam(name = "we_grade") Integer we_grade){
weprofile.setWe_grade(we_grade);
}


@PutMapping
("/setWe_noti_checked")
public void setWe_noti_checked(@RequestParam(name = "we_noti_checked") String we_noti_checked){
weprofile.setWe_noti_checked(we_noti_checked);
}


@PutMapping
("/setWe_visit_date")
public void setWe_visit_date(@RequestParam(name = "we_visit_date") Date we_visit_date){
weprofile.setWe_visit_date(we_visit_date);
}


}