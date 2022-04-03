package org.gliderwiki.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class AdminGroupServiceController {

 private AdminGroupService admingroupservice;


@GetMapping
("/insertGroupUser")
public int insertGroupUser(@RequestParam(name = "maps") Map<Integer,Map> maps){
  return admingroupservice.insertGroupUser(maps);
}


@GetMapping
("/insertGroupInfo")
public int insertGroupInfo(@RequestParam(name = "maps") Map<Integer,Map> maps){
  return admingroupservice.insertGroupInfo(maps);
}


}