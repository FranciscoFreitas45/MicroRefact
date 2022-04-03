package org.gliderwiki.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class RequestManagerController {

 private RequestManager requestmanager;


@GetMapping
("/getRemoteAddress")
public String getRemoteAddress(@RequestParam(name = "request") HttpServletRequest request){
  return requestmanager.getRemoteAddress(request);
}


}