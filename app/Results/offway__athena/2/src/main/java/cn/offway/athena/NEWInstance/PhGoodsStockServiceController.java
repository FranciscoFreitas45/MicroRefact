package cn.offway.athena.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class PhGoodsStockServiceController {

 private PhGoodsStockService phgoodsstockservice;


@GetMapping
("/updateStock")
public boolean updateStock(@RequestParam(name = "orderNo") String orderNo){
  return phgoodsstockservice.updateStock(orderNo);
}


}