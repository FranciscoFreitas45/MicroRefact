package org.gliderwiki.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class WeFileController {

 private WeFile wefile;

 private WeFile wefile;


@PutMapping
("/setWe_file_idx")
public void setWe_file_idx(@RequestParam(name = "we_file_idx") Integer we_file_idx){
wefile.setWe_file_idx(we_file_idx);
}


@PutMapping
("/setWe_file_real_name")
public void setWe_file_real_name(@RequestParam(name = "we_file_real_name") String we_file_real_name){
wefile.setWe_file_real_name(we_file_real_name);
}


@PutMapping
("/setWe_file_save_name")
public void setWe_file_save_name(@RequestParam(name = "we_file_save_name") String we_file_save_name){
wefile.setWe_file_save_name(we_file_save_name);
}


@PutMapping
("/setWe_file_save_path")
public void setWe_file_save_path(@RequestParam(name = "we_file_save_path") String we_file_save_path){
wefile.setWe_file_save_path(we_file_save_path);
}


@PutMapping
("/setWe_file_size")
public void setWe_file_size(@RequestParam(name = "we_file_size") String we_file_size){
wefile.setWe_file_size(we_file_size);
}


@PutMapping
("/setWe_file_type")
public void setWe_file_type(@RequestParam(name = "we_file_type") String we_file_type){
wefile.setWe_file_type(we_file_type);
}


@PutMapping
("/setWe_thumb_yn")
public void setWe_thumb_yn(@RequestParam(name = "we_thumb_yn") String we_thumb_yn){
wefile.setWe_thumb_yn(we_thumb_yn);
}


@PutMapping
("/setWe_thumb_name")
public void setWe_thumb_name(@RequestParam(name = "we_thumb_name") String we_thumb_name){
wefile.setWe_thumb_name(we_thumb_name);
}


@PutMapping
("/setWe_thumb_path")
public void setWe_thumb_path(@RequestParam(name = "we_thumb_path") String we_thumb_path){
wefile.setWe_thumb_path(we_thumb_path);
}


@PutMapping
("/setWe_user_idx")
public void setWe_user_idx(@RequestParam(name = "we_user_idx") Integer we_user_idx){
wefile.setWe_user_idx(we_user_idx);
}


@PutMapping
("/setWe_ins_date")
public void setWe_ins_date(@RequestParam(name = "we_ins_date") String we_ins_date){
wefile.setWe_ins_date(we_ins_date);
}


@PutMapping
("/setWe_ins_user")
public void setWe_ins_user(@RequestParam(name = "we_ins_user") String we_ins_user){
wefile.setWe_ins_user(we_ins_user);
}


}