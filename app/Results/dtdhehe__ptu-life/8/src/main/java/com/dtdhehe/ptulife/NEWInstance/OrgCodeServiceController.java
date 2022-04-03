package com.dtdhehe.ptulife.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class OrgCodeServiceController {

 private OrgCodeService orgcodeservice;


@GetMapping
("/getOrgNameByOrgStatus")
public String getOrgNameByOrgStatus(@RequestParam(name = "orgStatus") String orgStatus){
  return orgcodeservice.getOrgNameByOrgStatus(orgStatus);
}


}