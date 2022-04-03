package cn.offway.athena.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class PhOrderInfoServiceController {

 private PhOrderInfoService phorderinfoservice;


@GetMapping
("/findByOrderNo")
public PhOrderInfo findByOrderNo(@RequestParam(name = "orderNo") String orderNo){
  return phorderinfoservice.findByOrderNo(orderNo);
}


@GetMapping
("/save")
public PhOrderInfo save(@RequestParam(name = "phOrderInfo") PhOrderInfo phOrderInfo){
  return phorderinfoservice.save(phOrderInfo);
}


@GetMapping
("/generateOrderNo")
public String generateOrderNo(@RequestParam(name = "prefix") String prefix){
  return phorderinfoservice.generateOrderNo(prefix);
}


}