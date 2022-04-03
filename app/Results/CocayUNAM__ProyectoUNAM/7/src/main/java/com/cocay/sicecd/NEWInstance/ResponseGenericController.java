package com.cocay.sicecd.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ResponseGenericController {

 private ResponseGeneric responsegeneric;

 private ResponseGeneric responsegeneric;


@PutMapping
("/setNbMensaje")
public void setNbMensaje(@RequestParam(name = "nbMensaje") String nbMensaje){
responsegeneric.setNbMensaje(nbMensaje);
}


@PutMapping
("/setResponse")
public void setResponse(@RequestParam(name = "response") Object response){
responsegeneric.setResponse(response);
}


}