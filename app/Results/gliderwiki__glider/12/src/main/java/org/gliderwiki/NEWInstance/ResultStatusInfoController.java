package org.gliderwiki.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ResultStatusInfoController {

 private ResultStatusInfo resultstatusinfo;

 private ResultStatusInfo resultstatusinfo;


@PutMapping
("/setMessage")
public void setMessage(@RequestParam(name = "message") String message){
resultstatusinfo.setMessage(message);
}


@PutMapping
("/setRedirectUrl")
public void setRedirectUrl(@RequestParam(name = "redirectUrl") String redirectUrl){
resultstatusinfo.setRedirectUrl(redirectUrl);
}


}