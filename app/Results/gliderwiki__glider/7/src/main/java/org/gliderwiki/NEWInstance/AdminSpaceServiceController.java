package org.gliderwiki.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class AdminSpaceServiceController {

 private AdminSpaceService adminspaceservice;


@GetMapping
("/getSpaceSearchList")
public List<WeSpace> getSpaceSearchList(@RequestParam(name = "weSpace") WeSpace weSpace){
  return adminspaceservice.getSpaceSearchList(weSpace);
}


@GetMapping
("/getSpaceListMonth")
public List<WeSpace> getSpaceListMonth(@RequestParam(name = "space") WeSpace space,@RequestParam(name = "month") Date month){
  return adminspaceservice.getSpaceListMonth(space,month);
}


@GetMapping
("/getBbsSearchList")
public List<WeBbs> getBbsSearchList(@RequestParam(name = "weBbs") WeBbs weBbs){
  return adminspaceservice.getBbsSearchList(weBbs);
}


}