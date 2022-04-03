package com.sobey.cmop.mvc.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class MdnItemController {

 private MdnItem mdnitem;

 private MdnItem mdnitem;


@PutMapping
("/setCoverIsp")
public void setCoverIsp(@RequestParam(name = "coverIsp") String coverIsp){
mdnitem.setCoverIsp(coverIsp);
}


@PutMapping
("/setBandwidth")
public void setBandwidth(@RequestParam(name = "bandwidth") String bandwidth){
mdnitem.setBandwidth(bandwidth);
}


}