package com.project.stockexchangeappbackend.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ResourceRepositoryController {

 private ResourceRepository resourcerepository;


@PutMapping
("/deleteByStock")
public void deleteByStock(@RequestParam(name = "stock") Stock stock){
resourcerepository.deleteByStock(stock);
}


@PutMapping
("/deleteById")
public void deleteById(@RequestParam(name = "id") Long id){
resourcerepository.deleteById(id);
}


@GetMapping
("/findByUserAndStock")
public Optional<Resource> findByUserAndStock(@RequestParam(name = "user") User user,@RequestParam(name = "stock") Stock stock){
  return resourcerepository.findByUserAndStock(user,stock);
}


@GetMapping
("/findAll")
public Page<Resource> findAll(@RequestParam(name = "var1") Specification<Resource> var1,@RequestParam(name = "var2") Pageable var2){
  return resourcerepository.findAll(var1,var2);
}


@GetMapping
("/delete")
public Object delete(@RequestParam(name = "Object") Object Object){
  return resourcerepository.delete(Object);
}


@GetMapping
("/save")
public S save(@RequestParam(name = "s") S s){
  return resourcerepository.save(s);
}


@GetMapping
("/countByUser")
public Long countByUser(@RequestParam(name = "user") User user){
  return resourcerepository.countByUser(user);
}


@GetMapping
("/equals")
public Object equals(@RequestParam(name = "Object") Object Object){
  return resourcerepository.equals(Object);
}


}