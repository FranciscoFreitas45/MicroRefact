package org.danyuan.application.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class MultiDatasourceConfigController {

 private MultiDatasourceConfig multidatasourceconfig;


@GetMapping
("/getConnection")
public Connection getConnection(@RequestParam(name = "uuid") String uuid){
  return multidatasourceconfig.getConnection(uuid);
}


}