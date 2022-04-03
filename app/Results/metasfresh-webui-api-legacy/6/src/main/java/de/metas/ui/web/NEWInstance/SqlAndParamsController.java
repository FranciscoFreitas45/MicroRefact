package de.metas.ui.web.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class SqlAndParamsController {

 private SqlAndParams sqlandparams;


@GetMapping
("/of")
public SqlAndParams of(@RequestParam(name = "sql") CharSequence sql,@RequestParam(name = "sqlParamsArray") Object sqlParamsArray){
  return sqlandparams.of(sql,sqlParamsArray);
}


}