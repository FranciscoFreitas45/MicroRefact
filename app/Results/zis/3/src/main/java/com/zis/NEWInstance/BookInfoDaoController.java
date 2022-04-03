package com.zis.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class BookInfoDaoController {

 private BookInfoDao bookinfodao;


@GetMapping
("/findOne")
public Object findOne(@RequestParam(name = "Object") Object Object){
  return bookinfodao.findOne(Object);
}


}