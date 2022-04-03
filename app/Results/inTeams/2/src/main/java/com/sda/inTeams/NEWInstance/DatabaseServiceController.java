package com.sda.inTeams.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class DatabaseServiceController {

 private DatabaseService databaseservice;


@GetMapping
("/getDatabaseInfo")
public DatabaseInfo getDatabaseInfo(){
  return databaseservice.getDatabaseInfo();
}


}