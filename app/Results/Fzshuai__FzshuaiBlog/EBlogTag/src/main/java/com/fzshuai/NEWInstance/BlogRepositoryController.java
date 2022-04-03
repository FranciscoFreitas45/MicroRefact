package com.fzshuai.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class BlogRepositoryController {

 private BlogRepository blogrepository;


@GetMapping
("/findById")
public Object findById(@RequestParam(name = "Object") Object Object){
  return blogrepository.findById(Object);
}


@GetMapping
("/updateViews")
public int updateViews(@RequestParam(name = "id") Long id){
  return blogrepository.updateViews(id);
}


@GetMapping
("/findAll")
public Object findAll(@RequestParam(name = "Object") Object Object){
  return blogrepository.findAll(Object);
}


@GetMapping
("/findByQuery")
public Page<Blog> findByQuery(@RequestParam(name = "query") String query,@RequestParam(name = "pageable") Pageable pageable){
  return blogrepository.findByQuery(query,pageable);
}


@GetMapping
("/findTop")
public List<Blog> findTop(@RequestParam(name = "pageable") Pageable pageable){
  return blogrepository.findTop(pageable);
}


@GetMapping
("/findGroupYear")
public List<String> findGroupYear(){
  return blogrepository.findGroupYear();
}


@GetMapping
("/findByYear")
public List<Blog> findByYear(@RequestParam(name = "year") String year){
  return blogrepository.findByYear(year);
}


@GetMapping
("/count")
public Object count(@RequestParam(name = "Object") Object Object){
  return blogrepository.count(Object);
}


@GetMapping
("/save")
public Object save(@RequestParam(name = "Object") Object Object){
  return blogrepository.save(Object);
}


@GetMapping
("/deleteById")
public Object deleteById(@RequestParam(name = "Object") Object Object){
  return blogrepository.deleteById(Object);
}


}