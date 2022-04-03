package com.cym.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class VersionConfigController {

 private VersionConfig versionconfig;


@PutMapping
("/getNewVersion")
public void getNewVersion(){
versionconfig.getNewVersion();
}


}