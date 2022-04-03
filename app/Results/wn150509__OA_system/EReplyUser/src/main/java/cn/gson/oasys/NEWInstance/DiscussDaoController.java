package cn.gson.oasys.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class DiscussDaoController {

 private DiscussDao discussdao;


@GetMapping
("/count")
public Object count(@RequestParam(name = "Object") Object Object){
  return discussdao.count(Object);
}


@GetMapping
("/save")
public Object save(@RequestParam(name = "Object") Object Object){
  return discussdao.save(Object);
}


@GetMapping
("/delete")
public Object delete(@RequestParam(name = "Object") Object Object){
  return discussdao.delete(Object);
}


@GetMapping
("/findOne")
public Object findOne(@RequestParam(name = "Object") Object Object){
  return discussdao.findOne(Object);
}


@GetMapping
("/findByUserAndTitleLike")
public Page<Discuss> findByUserAndTitleLike(@RequestParam(name = "user") User user,@RequestParam(name = "title") String title,@RequestParam(name = "pa") Pageable pa){
  return discussdao.findByUserAndTitleLike(user,title,pa);
}


@GetMapping
("/findByUser")
public Page<Discuss> findByUser(@RequestParam(name = "user") User user,@RequestParam(name = "pa") Pageable pa){
  return discussdao.findByUser(user,pa);
}


@GetMapping
("/findByTitleLike")
public Page<Discuss> findByTitleLike(@RequestParam(name = "title") String title,@RequestParam(name = "pa") Pageable pa){
  return discussdao.findByTitleLike(title,pa);
}


@GetMapping
("/findAll")
public Object findAll(@RequestParam(name = "Object") Object Object){
  return discussdao.findAll(Object);
}


}