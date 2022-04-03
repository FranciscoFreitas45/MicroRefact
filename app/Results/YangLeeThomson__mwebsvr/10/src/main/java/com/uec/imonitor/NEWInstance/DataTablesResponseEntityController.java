package com.uec.imonitor.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class DataTablesResponseEntityController {

 private DataTablesResponseEntity datatablesresponseentity;

 private DataTablesResponseEntity datatablesresponseentity;


@PutMapping
("/setiTotalRecords")
public void setiTotalRecords(@RequestParam(name = "iTotalRecords") long iTotalRecords){
datatablesresponseentity.setiTotalRecords(iTotalRecords);
}


@PutMapping
("/setsEcho")
public void setsEcho(@RequestParam(name = "sEcho") int sEcho){
datatablesresponseentity.setsEcho(sEcho);
}


@PutMapping
("/setAaData")
public void setAaData(@RequestParam(name = "list") List<T> list){
datatablesresponseentity.setAaData(list);
}


}