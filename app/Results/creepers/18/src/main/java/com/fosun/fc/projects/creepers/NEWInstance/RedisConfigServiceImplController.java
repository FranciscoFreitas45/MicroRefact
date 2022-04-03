package com.fosun.fc.projects.creepers.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class RedisConfigServiceImplController {

 private RedisConfigServiceImpl redisconfigserviceimpl;

 private RedisConfigServiceImpl redisconfigserviceimpl;


@PutMapping
("/refresh")
public void refresh(){
redisconfigserviceimpl.refresh();
}


}