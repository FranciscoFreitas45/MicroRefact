package com.easyshopping.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class PromotionDaoController {

 private PromotionDao promotiondao;


@GetMapping
("/findList")
public List<Promotion> findList(@RequestParam(name = "hasBegun") Boolean hasBegun,@RequestParam(name = "hasEnded") Boolean hasEnded,@RequestParam(name = "count") Integer count,@RequestParam(name = "filters") List<Filter> filters,@RequestParam(name = "orders") List<Order> orders){
  return promotiondao.findList(hasBegun,hasEnded,count,filters,orders);
}


@GetMapping
("/clear")
public Object clear(@RequestParam(name = "Object") Object Object){
  return promotiondao.clear(Object);
}


@GetMapping
("/flush")
public Object flush(@RequestParam(name = "Object") Object Object){
  return promotiondao.flush(Object);
}


}