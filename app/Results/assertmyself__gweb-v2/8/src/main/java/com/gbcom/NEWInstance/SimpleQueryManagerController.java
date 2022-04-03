package com.gbcom.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class SimpleQueryManagerController {

 private SimpleQueryManager simplequerymanager;


@GetMapping
("/getStringBySql")
public String getStringBySql(@RequestParam(name = "sql") String sql){
  return simplequerymanager.getStringBySql(sql);
}


}