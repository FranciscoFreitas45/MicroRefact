package com.cym.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class JsonResultController {

 private JsonResult jsonresult;

 private JsonResult jsonresult;


@PutMapping
("/setStatus")
public void setStatus(@RequestParam(name = "status") String status){
jsonresult.setStatus(status);
}


@PutMapping
("/setObj")
public void setObj(@RequestParam(name = "obj") T obj){
jsonresult.setObj(obj);
}


@PutMapping
("/setMsg")
public void setMsg(@RequestParam(name = "msg") String msg){
jsonresult.setMsg(msg);
}


@GetMapping
("/isSuccess")
public boolean isSuccess(){
  return jsonresult.isSuccess();
}


@PutMapping
("/setSuccess")
public void setSuccess(@RequestParam(name = "success") boolean success){
jsonresult.setSuccess(success);
}


}