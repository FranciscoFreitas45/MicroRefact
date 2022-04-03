package com.project.stockexchangeappbackend.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class UserRepositoryController {

 private UserRepository userrepository;


@GetMapping
("/findById")
public Optional<User> findById(@RequestParam(name = "id") Long id){
  return userrepository.findById(id);
}


@GetMapping
("/findByEmailIgnoreCase")
public Optional<User> findByEmailIgnoreCase(@RequestParam(name = "email") String email){
  return userrepository.findByEmailIgnoreCase(email);
}


@GetMapping
("/save")
public S save(@RequestParam(name = "s") S s){
  return userrepository.save(s);
}


@GetMapping
("/findAll")
public Page<User> findAll(@RequestParam(name = "specification") Specification<User> specification,@RequestParam(name = "pageable") Pageable pageable){
  return userrepository.findAll(specification,pageable);
}


@GetMapping
("/countByRole")
public Long countByRole(@RequestParam(name = "role") Role role){
  return userrepository.countByRole(role);
}


@GetMapping
("/equals")
public Object equals(@RequestParam(name = "Object") Object Object){
  return userrepository.equals(Object);
}


}