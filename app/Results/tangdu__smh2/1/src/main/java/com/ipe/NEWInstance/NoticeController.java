package com.ipe.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class NoticeController {

 private Notice notice;

 private Notice notice;


@PutMapping
("/setUserId")
public void setUserId(@RequestParam(name = "userId") String userId){
notice.setUserId(userId);
}


@PutMapping
("/setCreatedDate")
public void setCreatedDate(@RequestParam(name = "createdDate") Date createdDate){
notice.setCreatedDate(createdDate);
}


@PutMapping
("/setAppendixPath")
public void setAppendixPath(@RequestParam(name = "appendixPath") String appendixPath){
notice.setAppendixPath(appendixPath);
}


}