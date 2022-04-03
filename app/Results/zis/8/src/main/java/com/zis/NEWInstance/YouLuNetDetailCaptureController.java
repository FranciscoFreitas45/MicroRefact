package com.zis.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class YouLuNetDetailCaptureController {

 private YouLuNetDetailCapture youlunetdetailcapture;


@GetMapping
("/getBookIdByIsbn")
public Integer getBookIdByIsbn(@RequestParam(name = "isbn") String isbn){
  return youlunetdetailcapture.getBookIdByIsbn(isbn);
}


}