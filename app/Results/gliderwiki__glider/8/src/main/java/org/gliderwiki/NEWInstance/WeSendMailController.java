package org.gliderwiki.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class WeSendMailController {

 private WeSendMail wesendmail;

 private WeSendMail wesendmail;


@PutMapping
("/setWe_ins_date")
public void setWe_ins_date(@RequestParam(name = "we_ins_date") Date we_ins_date){
wesendmail.setWe_ins_date(we_ins_date);
}


@PutMapping
("/setWe_mail_idx")
public void setWe_mail_idx(@RequestParam(name = "we_mail_idx") Integer we_mail_idx){
wesendmail.setWe_mail_idx(we_mail_idx);
}


@PutMapping
("/setWe_send_status")
public void setWe_send_status(@RequestParam(name = "we_send_status") String we_send_status){
wesendmail.setWe_send_status(we_send_status);
}


@PutMapping
("/setWe_upd_date")
public void setWe_upd_date(@RequestParam(name = "we_upd_date") Date we_upd_date){
wesendmail.setWe_upd_date(we_upd_date);
}


}