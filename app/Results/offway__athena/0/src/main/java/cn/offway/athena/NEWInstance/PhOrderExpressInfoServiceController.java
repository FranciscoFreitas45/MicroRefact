package cn.offway.athena.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class PhOrderExpressInfoServiceController {

 private PhOrderExpressInfoService phorderexpressinfoservice;


@GetMapping
("/findByExpressOrderNo")
public PhOrderExpressInfo findByExpressOrderNo(@RequestParam(name = "expressOrderNo") String expressOrderNo){
  return phorderexpressinfoservice.findByExpressOrderNo(expressOrderNo);
}


@GetMapping
("/save")
public PhOrderExpressInfo save(@RequestParam(name = "phOrderExpressInfo") PhOrderExpressInfo phOrderExpressInfo){
  return phorderexpressinfoservice.save(phOrderExpressInfo);
}


}