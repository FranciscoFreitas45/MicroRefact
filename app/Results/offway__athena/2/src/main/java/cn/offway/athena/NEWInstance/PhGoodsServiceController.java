package cn.offway.athena.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class PhGoodsServiceController {

 private PhGoodsService phgoodsservice;


@GetMapping
("/findOne")
public PhGoods findOne(@RequestParam(name = "id") Long id){
  return phgoodsservice.findOne(id);
}


@GetMapping
("/findAll")
public List<PhGoods> findAll(@RequestParam(name = "name") String name,@RequestParam(name = "value") Object value,@RequestParam(name = "brandId") String brandId){
  return phgoodsservice.findAll(name,value,brandId);
}


@GetMapping
("/findByPage")
public Page<PhGoods> findByPage(@RequestParam(name = "name") String name,@RequestParam(name = "brandId") Long brandId,@RequestParam(name = "isOffway") String isOffway,@RequestParam(name = "brandIds") List<Long> brandIds,@RequestParam(name = "status") String status,@RequestParam(name = "type") String type,@RequestParam(name = "category") String category,@RequestParam(name = "page") Pageable page){
  return phgoodsservice.findByPage(name,brandId,isOffway,brandIds,status,type,category,page);
}


@GetMapping
("/save")
public List<PhGoods> save(@RequestParam(name = "phGoods") List<PhGoods> phGoods){
  return phgoodsservice.save(phGoods);
}


@GetMapping
("/imagesDelete")
public boolean imagesDelete(@RequestParam(name = "goodsImageId") Long goodsImageId){
  return phgoodsservice.imagesDelete(goodsImageId);
}


@GetMapping
("/findByBrandId")
public List<PhGoods> findByBrandId(@RequestParam(name = "brandId") Long brandId){
  return phgoodsservice.findByBrandId(brandId);
}


@GetMapping
("/delete")
public String delete(@RequestParam(name = "goodsIds") List<Long> goodsIds){
  return phgoodsservice.delete(goodsIds);
}


}