package cn.offway.athena.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class PhOrderGoodsRepositoryController {

 private PhOrderGoodsRepository phordergoodsrepository;


@GetMapping
("/countByGoodsIds")
public int countByGoodsIds(@RequestParam(name = "goodsIds") List<Long> goodsIds){
  return phordergoodsrepository.countByGoodsIds(goodsIds);
}


}