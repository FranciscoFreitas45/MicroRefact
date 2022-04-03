package com.gbcom.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class SqlImportManagerController {

 private SqlImportManager sqlimportmanager;

 private SqlImportManager sqlimportmanager;


@GetMapping
("/importSql")
public boolean importSql(@RequestParam(name = "filePath") String filePath){
  return sqlimportmanager.importSql(filePath);
}


}