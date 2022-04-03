package com.zis.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class DoPurchaseServiceDWRController {

 private DoPurchaseServiceDWR dopurchaseservicedwr;


@GetMapping
("/addBlackList")
public String addBlackList(@RequestParam(name = "bookId") Integer bookId){
  return dopurchaseservicedwr.addBlackList(bookId);
}


}