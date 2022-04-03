package io.swagger.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class OrderRepositoryController {

 private OrderRepository orderrepository;


@GetMapping
("/findByUser")
public List<Order> findByUser(@RequestParam(name = "user") User user){
  return orderrepository.findByUser(user);
}


@GetMapping
("/save")
public Object save(@RequestParam(name = "Object") Object Object){
  return orderrepository.save(Object);
}


}