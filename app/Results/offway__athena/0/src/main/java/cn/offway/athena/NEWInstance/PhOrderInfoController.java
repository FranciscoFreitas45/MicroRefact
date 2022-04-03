package cn.offway.athena.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class PhOrderInfoController {

 private PhOrderInfo phorderinfo;

 private PhOrderInfo phorderinfo;


@PutMapping
("/setReceiptTime")
public void setReceiptTime(@RequestParam(name = "receiptTime") Date receiptTime){
phorderinfo.setReceiptTime(receiptTime);
}


@PutMapping
("/setIsUpload")
public void setIsUpload(@RequestParam(name = "isUpload") String isUpload){
phorderinfo.setIsUpload(isUpload);
}


}