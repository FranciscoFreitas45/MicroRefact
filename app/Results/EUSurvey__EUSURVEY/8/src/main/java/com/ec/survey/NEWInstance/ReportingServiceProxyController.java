package com.ec.survey.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ReportingServiceProxyController {

 private ReportingServiceProxy reportingserviceproxy;


@GetMapping
("/getCount")
public int getCount(@RequestParam(name = "survey") Survey survey,@RequestParam(name = "quid") String quid,@RequestParam(name = "auid") String auid,@RequestParam(name = "noPrefixSearch") boolean noPrefixSearch,@RequestParam(name = "noPostfixSearch") boolean noPostfixSearch,@RequestParam(name = "where") String where,@RequestParam(name = "values") Map<String,Object> values){
  return reportingserviceproxy.getCount(survey,quid,auid,noPrefixSearch,noPostfixSearch,where,values);
}


@GetMapping
("/OLAPTableExists")
public boolean OLAPTableExists(@RequestParam(name = "uid") String uid,@RequestParam(name = "draft") boolean draft){
  return reportingserviceproxy.OLAPTableExists(uid,draft);
}


}