package com.softserve.edu.Resources.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ExceptionJSONInfoController {

 private ExceptionJSONInfo exceptionjsoninfo;

 private ExceptionJSONInfo exceptionjsoninfo;


@PutMapping
("/setUrl")
public void setUrl(@RequestParam(name = "url") String url){
exceptionjsoninfo.setUrl(url);
}


@PutMapping
("/setMessage")
public void setMessage(@RequestParam(name = "message") String message){
exceptionjsoninfo.setMessage(message);
}


}