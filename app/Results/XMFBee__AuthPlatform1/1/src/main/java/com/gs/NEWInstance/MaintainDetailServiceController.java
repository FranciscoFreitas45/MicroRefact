package com.gs.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class MaintainDetailServiceController {

 private MaintainDetailService maintaindetailservice;


@GetMapping
("/queryByFrontpage")
public List<MaintainDetail> queryByFrontpage(@RequestParam(name = "pager") Pager pager){
  return maintaindetailservice.queryByFrontpage(pager);
}


}