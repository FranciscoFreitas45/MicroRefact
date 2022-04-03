package org.gliderwiki.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class WeUserAlarmController {

 private WeUserAlarm weuseralarm;

 private WeUserAlarm weuseralarm;


@PutMapping
("/setWe_meta_idx")
public void setWe_meta_idx(@RequestParam(name = "we_meta_idx") Integer we_meta_idx){
weuseralarm.setWe_meta_idx(we_meta_idx);
}


@PutMapping
("/setWe_use_yn")
public void setWe_use_yn(@RequestParam(name = "we_use_yn") String we_use_yn){
weuseralarm.setWe_use_yn(we_use_yn);
}


}