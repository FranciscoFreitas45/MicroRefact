package cn.offway.athena.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class PhOrderGoodsServiceController {

 private PhOrderGoodsService phordergoodsservice;


@GetMapping
("/findByOrderNo")
public List<PhOrderGoods> findByOrderNo(@RequestParam(name = "orderNo") String orderNo){
  return phordergoodsservice.findByOrderNo(orderNo);
}


@GetMapping
("/findByBrandId")
public Page<PhOrderGoods> findByBrandId(@RequestParam(name = "brandId") String brandId,@RequestParam(name = "page") Pageable page){
  return phordergoodsservice.findByBrandId(brandId,page);
}


}