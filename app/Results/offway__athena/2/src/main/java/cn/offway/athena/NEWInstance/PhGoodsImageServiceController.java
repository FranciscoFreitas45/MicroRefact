package cn.offway.athena.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class PhGoodsImageServiceController {

 private PhGoodsImageService phgoodsimageservice;


@GetMapping
("/findByGoodsId")
public List<PhGoodsImage> findByGoodsId(@RequestParam(name = "goodsId") Long goodsId){
  return phgoodsimageservice.findByGoodsId(goodsId);
}


}