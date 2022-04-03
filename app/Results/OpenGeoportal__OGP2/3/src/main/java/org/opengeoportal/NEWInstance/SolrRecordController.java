package org.opengeoportal.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class SolrRecordController {

 private SolrRecord solrrecord;


@GetMapping
("/getLayerId")
public String getLayerId(){
  return solrrecord.getLayerId();
}


@GetMapping
("/getWorkspaceName")
public String getWorkspaceName(){
  return solrrecord.getWorkspaceName();
}


@GetMapping
("/getName")
public String getName(){
  return solrrecord.getName();
}


@GetMapping
("/getLocation")
public String getLocation(){
  return solrrecord.getLocation();
}


}