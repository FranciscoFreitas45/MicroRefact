package com.ec.survey.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class SchemaServiceController {

 private SchemaService schemaservice;


@GetMapping
("/getLastAnswerSetAnonymDate")
public Date getLastAnswerSetAnonymDate(){
  return schemaservice.getLastAnswerSetAnonymDate();
}


@PutMapping
("/saveLastAnswerSetAnonymDate")
public void saveLastAnswerSetAnonymDate(@RequestParam(name = "lastAnswerSetAnonymisedDate") Date lastAnswerSetAnonymisedDate){
schemaservice.saveLastAnswerSetAnonymDate(lastAnswerSetAnonymisedDate);
}


@GetMapping
("/getLastLDAPSynchronization2Date")
public Date getLastLDAPSynchronization2Date(){
  return schemaservice.getLastLDAPSynchronization2Date();
}


@GetMapping
("/getLastLDAPSynchronizationDate")
public Date getLastLDAPSynchronizationDate(){
  return schemaservice.getLastLDAPSynchronizationDate();
}


@PutMapping
("/saveLastLDAPSynchronizationDate")
public void saveLastLDAPSynchronizationDate(@RequestParam(name = "syncDate") Date syncDate){
schemaservice.saveLastLDAPSynchronizationDate(syncDate);
}


@PutMapping
("/saveLastLDAPSynchronization2Date")
public void saveLastLDAPSynchronization2Date(@RequestParam(name = "syncDate") Date syncDate){
schemaservice.saveLastLDAPSynchronization2Date(syncDate);
}


}