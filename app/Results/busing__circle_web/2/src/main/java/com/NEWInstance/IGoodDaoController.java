package com.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class IGoodDaoController {

 private IGoodDao igooddao;


@PutMapping
("/addBuyNum")
public void addBuyNum(@RequestParam(name = "goodId") String goodId,@RequestParam(name = "buyNum") int buyNum){
igooddao.addBuyNum(goodId,buyNum);
}


@PutMapping
("/batchAddBuyNum")
public void batchAddBuyNum(@RequestParam(name = "paramListMap") List<Map<String,?>> paramListMap){
igooddao.batchAddBuyNum(paramListMap);
}


@GetMapping
("/queryGoodList")
public List<Map<String,Object>> queryGoodList(@RequestParam(name = "page") Page page){
  return igooddao.queryGoodList(page);
}


@GetMapping
("/queryCurrentBatchGoodList")
public List<Map<String,Object>> queryCurrentBatchGoodList(@RequestParam(name = "page") Page page,@RequestParam(name = "batchId") int batchId){
  return igooddao.queryCurrentBatchGoodList(page,batchId);
}


@GetMapping
("/queryGood")
public Map<String,Object> queryGood(@RequestParam(name = "id") String id){
  return igooddao.queryGood(id);
}


@GetMapping
("/queryGoodTypeArgValues")
public List<Map<String,Object>> queryGoodTypeArgValues(@RequestParam(name = "id") String id){
  return igooddao.queryGoodTypeArgValues(id);
}


@GetMapping
("/queryGoodTypeAttrValues")
public List<Map<String,Object>> queryGoodTypeAttrValues(@RequestParam(name = "id") String id){
  return igooddao.queryGoodTypeAttrValues(id);
}


@GetMapping
("/queryImageList")
public List<Map<String,Object>> queryImageList(@RequestParam(name = "id") String id){
  return igooddao.queryImageList(id);
}


@GetMapping
("/queryGoodComment")
public List<Map<String,Object>> queryGoodComment(@RequestParam(name = "page") Page page){
  return igooddao.queryGoodComment(page);
}


}