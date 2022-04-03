package org.gliderwiki.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class WeWikiCommentController {

 private WeWikiComment wewikicomment;

 private WeWikiComment wewikicomment;


@PutMapping
("/setWe_use_yn")
public void setWe_use_yn(@RequestParam(name = "we_use_yn") String we_use_yn){
wewikicomment.setWe_use_yn(we_use_yn);
}


@PutMapping
("/setWe_ins_user")
public void setWe_ins_user(@RequestParam(name = "we_ins_user") Integer we_ins_user){
wewikicomment.setWe_ins_user(we_ins_user);
}


@PutMapping
("/setWe_ins_date")
public void setWe_ins_date(@RequestParam(name = "we_ins_date") Date we_ins_date){
wewikicomment.setWe_ins_date(we_ins_date);
}


@PutMapping
("/setWe_wiki_idx")
public void setWe_wiki_idx(@RequestParam(name = "we_wiki_idx") Integer we_wiki_idx){
wewikicomment.setWe_wiki_idx(we_wiki_idx);
}


@PutMapping
("/setWe_bbs_text")
public void setWe_bbs_text(@RequestParam(name = "we_bbs_text") String we_bbs_text){
wewikicomment.setWe_bbs_text(we_bbs_text);
}


@PutMapping
("/setWe_user_ip")
public void setWe_user_ip(@RequestParam(name = "we_user_ip") String we_user_ip){
wewikicomment.setWe_user_ip(we_user_ip);
}


@PutMapping
("/setWe_ins_name")
public void setWe_ins_name(@RequestParam(name = "we_ins_name") String we_ins_name){
wewikicomment.setWe_ins_name(we_ins_name);
}


@PutMapping
("/setWe_wiki_comment_idx")
public void setWe_wiki_comment_idx(@RequestParam(name = "we_wiki_comment_idx") Integer we_wiki_comment_idx){
wewikicomment.setWe_wiki_comment_idx(we_wiki_comment_idx);
}


}