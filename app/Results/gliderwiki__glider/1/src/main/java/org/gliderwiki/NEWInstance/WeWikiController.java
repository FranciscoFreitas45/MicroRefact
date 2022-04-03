package org.gliderwiki.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class WeWikiController {

 private WeWiki wewiki;

 private WeWiki wewiki;


@PutMapping
("/setWe_wiki_idx")
public void setWe_wiki_idx(@RequestParam(name = "we_wiki_idx") Integer we_wiki_idx){
wewiki.setWe_wiki_idx(we_wiki_idx);
}


@PutMapping
("/setWe_use_yn")
public void setWe_use_yn(@RequestParam(name = "we_use_yn") String we_use_yn){
wewiki.setWe_use_yn(we_use_yn);
}


@PutMapping
("/setWe_upd_user")
public void setWe_upd_user(@RequestParam(name = "we_upd_user") Integer we_upd_user){
wewiki.setWe_upd_user(we_upd_user);
}


@PutMapping
("/setWe_wiki_protect")
public void setWe_wiki_protect(@RequestParam(name = "we_wiki_protect") String we_wiki_protect){
wewiki.setWe_wiki_protect(we_wiki_protect);
}


@PutMapping
("/setWe_upd_date")
public void setWe_upd_date(@RequestParam(name = "we_upd_date") Date we_upd_date){
wewiki.setWe_upd_date(we_upd_date);
}


@PutMapping
("/setWe_wiki_revision")
public void setWe_wiki_revision(@RequestParam(name = "we_wiki_revision") Integer we_wiki_revision){
wewiki.setWe_wiki_revision(we_wiki_revision);
}


@PutMapping
("/setWe_wiki_title")
public void setWe_wiki_title(@RequestParam(name = "we_wiki_title") String we_wiki_title){
wewiki.setWe_wiki_title(we_wiki_title);
}


@PutMapping
("/setWe_wiki_text")
public void setWe_wiki_text(@RequestParam(name = "we_wiki_text") String we_wiki_text){
wewiki.setWe_wiki_text(we_wiki_text);
}


@PutMapping
("/setWe_ins_date")
public void setWe_ins_date(@RequestParam(name = "we_ins_date") Date we_ins_date){
wewiki.setWe_ins_date(we_ins_date);
}


@PutMapping
("/setWe_ins_user")
public void setWe_ins_user(@RequestParam(name = "we_ins_user") Integer we_ins_user){
wewiki.setWe_ins_user(we_ins_user);
}


@PutMapping
("/setWe_edit_text")
public void setWe_edit_text(@RequestParam(name = "we_edit_text") String we_edit_text){
wewiki.setWe_edit_text(we_edit_text);
}


@PutMapping
("/setWe_user_nick")
public void setWe_user_nick(@RequestParam(name = "we_user_nick") String we_user_nick){
wewiki.setWe_user_nick(we_user_nick);
}


@PutMapping
("/setWe_wiki_markup")
public void setWe_wiki_markup(@RequestParam(name = "we_wiki_markup") String we_wiki_markup){
wewiki.setWe_wiki_markup(we_wiki_markup);
}


@PutMapping
("/setWe_wiki_status")
public void setWe_wiki_status(@RequestParam(name = "we_wiki_status") String we_wiki_status){
wewiki.setWe_wiki_status(we_wiki_status);
}


@PutMapping
("/setWe_user_ip")
public void setWe_user_ip(@RequestParam(name = "we_user_ip") String we_user_ip){
wewiki.setWe_user_ip(we_user_ip);
}


@PutMapping
("/newBasicSetting")
public void newBasicSetting(@RequestParam(name = "usrIdx") Integer usrIdx,@RequestParam(name = "saveType") String saveType,@RequestParam(name = "usrIp") String usrIp){
wewiki.newBasicSetting(usrIdx,saveType,usrIp);
}


@PutMapping
("/setWe_wiki_agree")
public void setWe_wiki_agree(@RequestParam(name = "we_wiki_agree") Integer we_wiki_agree){
wewiki.setWe_wiki_agree(we_wiki_agree);
}


@PutMapping
("/setWe_edit_yn")
public void setWe_edit_yn(@RequestParam(name = "we_edit_yn") String we_edit_yn){
wewiki.setWe_edit_yn(we_edit_yn);
}


}