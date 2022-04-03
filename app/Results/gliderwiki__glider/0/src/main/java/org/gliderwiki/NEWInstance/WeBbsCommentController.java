package org.gliderwiki.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class WeBbsCommentController {

 private WeBbsComment webbscomment;

 private WeBbsComment webbscomment;


@PutMapping
("/setWe_use_yn")
public void setWe_use_yn(@RequestParam(name = "we_use_yn") String we_use_yn){
webbscomment.setWe_use_yn(we_use_yn);
}


}