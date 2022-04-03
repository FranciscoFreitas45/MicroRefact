package com.wxcrm.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class IHibernateDaoController {

 private IHibernateDao ihibernatedao;


@GetMapping
("/add")
public Serializable add(@RequestParam(name = "entity") Object entity){
  return ihibernatedao.add(entity);
}


@GetMapping
("/get")
public T get(@RequestParam(name = "entityClass") Class<T> entityClass,@RequestParam(name = "id") Serializable id){
  return ihibernatedao.get(entityClass,id);
}


@PutMapping
("/update")
public void update(@RequestParam(name = "entity") Object entity){
ihibernatedao.update(entity);
}


@PutMapping
("/flush")
public void flush(){
ihibernatedao.flush();
}


@GetMapping
("/query")
public List<T> query(@RequestParam(name = "hql") String hql,@RequestParam(name = "args") Object[] args,@RequestParam(name = "index") int index,@RequestParam(name = "max") int max){
  return ihibernatedao.query(hql,args,index,max);
}


}