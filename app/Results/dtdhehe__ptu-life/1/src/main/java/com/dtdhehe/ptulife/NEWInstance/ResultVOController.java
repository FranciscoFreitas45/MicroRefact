package com.dtdhehe.ptulife.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ResultVOController {

 private ResultVO resultvo;

 private ResultVO resultvo;


@PutMapping
("/setStatus")
public void setStatus(@RequestParam(name = "status") String status){
resultvo.setStatus(status);
}


@PutMapping
("/setError_code")
public void setError_code(@RequestParam(name = "error_code") String error_code){
resultvo.setError_code(error_code);
}


@PutMapping
("/setError_msg")
public void setError_msg(@RequestParam(name = "error_msg") String error_msg){
resultvo.setError_msg(error_msg);
}


@PutMapping
("/setObject")
public void setObject(@RequestParam(name = "object") Object object){
resultvo.setObject(object);
}


}