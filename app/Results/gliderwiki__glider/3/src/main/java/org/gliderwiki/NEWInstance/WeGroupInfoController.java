package org.gliderwiki.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class WeGroupInfoController {

 private WeGroupInfo wegroupinfo;

 private WeGroupInfo wegroupinfo;


@PutMapping
("/setWe_group_idx")
public void setWe_group_idx(@RequestParam(name = "we_group_idx") Integer we_group_idx){
wegroupinfo.setWe_group_idx(we_group_idx);
}


}