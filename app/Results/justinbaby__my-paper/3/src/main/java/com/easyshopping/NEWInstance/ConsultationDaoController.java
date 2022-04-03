package com.easyshopping.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ConsultationDaoController {

 private ConsultationDao consultationdao;


@GetMapping
("/findList")
public List<Consultation> findList(@RequestParam(name = "member") Member member,@RequestParam(name = "product") Product product,@RequestParam(name = "isShow") Boolean isShow,@RequestParam(name = "count") Integer count,@RequestParam(name = "filters") List<Filter> filters,@RequestParam(name = "orders") List<Order> orders){
  return consultationdao.findList(member,product,isShow,count,filters,orders);
}


@GetMapping
("/findPage")
public Page<Consultation> findPage(@RequestParam(name = "member") Member member,@RequestParam(name = "product") Product product,@RequestParam(name = "isShow") Boolean isShow,@RequestParam(name = "pageable") Pageable pageable){
  return consultationdao.findPage(member,product,isShow,pageable);
}


@GetMapping
("/count")
public Long count(@RequestParam(name = "member") Member member,@RequestParam(name = "product") Product product,@RequestParam(name = "isShow") Boolean isShow){
  return consultationdao.count(member,product,isShow);
}


@GetMapping
("/merge")
public Object merge(@RequestParam(name = "Object") Object Object){
  return consultationdao.merge(Object);
}


@GetMapping
("/persist")
public Object persist(@RequestParam(name = "Object") Object Object){
  return consultationdao.persist(Object);
}


@GetMapping
("/flush")
public Object flush(@RequestParam(name = "Object") Object Object){
  return consultationdao.flush(Object);
}


}