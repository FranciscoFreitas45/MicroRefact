package com.project.stockexchangeappbackend.NEWInstance;
 import org.springframework.web.bind.annotation.*;
 import com.project.stockexchangeappbackend.repository.AllOrdersRepository;
 import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
 import com.project.stockexchangeappbackend.service.*;
 import com.project.stockexchangeappbackend.entity.*;
 import com.project.stockexchangeappbackend.DTO.*;
 import java.util.*;
@RestController
@CrossOrigin
public class AllOrdersRepositoryController {

 private AllOrdersRepository allordersrepository;


@GetMapping
("/countByUser")
public Long countByUser(@RequestParam(name = "user") User user){
  return allordersrepository.countByUser(user);
}

/*
@GetMapping
("/equals")
public Boolean equals(@RequestParam(name = "Object") Object Object){
  return allordersrepository.equals(Object);
}
*/

}