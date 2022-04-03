package com.zis.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class CheckOutIdDTOController {

 private CheckOutIdDTO checkoutiddto;

 private CheckOutIdDTO checkoutiddto;


@PutMapping
("/setFailMsg")
public void setFailMsg(@RequestParam(name = "failMsg") String failMsg){
checkoutiddto.setFailMsg(failMsg);
}


@PutMapping
("/setIsSuccess")
public void setIsSuccess(@RequestParam(name = "isSuccess") boolean isSuccess){
checkoutiddto.setIsSuccess(isSuccess);
}


@PutMapping
("/setBook")
public void setBook(@RequestParam(name = "book") Bookinfo book){
checkoutiddto.setBook(book);
}


}