package com.ec.survey.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class SqlQueryServiceController {

 private SqlQueryService sqlqueryservice;


@PutMapping
("/setParameters")
public void setParameters(@RequestParam(name = "query") Query query,@RequestParam(name = "parameters") Map<String,Object> parameters){
sqlqueryservice.setParameters(query,parameters);
}


}