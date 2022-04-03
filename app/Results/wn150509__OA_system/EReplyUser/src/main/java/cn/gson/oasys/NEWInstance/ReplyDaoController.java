package cn.gson.oasys.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ReplyDaoController {

 private ReplyDao replydao;


@GetMapping
("/findByDiscuss")
public List<Reply> findByDiscuss(@RequestParam(name = "discuss") Discuss discuss){
  return replydao.findByDiscuss(discuss);
}


@GetMapping
("/findByDiscussAndUser")
public Page<Reply> findByDiscussAndUser(@RequestParam(name = "discuss") Discuss discuss,@RequestParam(name = "user") User user,@RequestParam(name = "pa") Pageable pa){
  return replydao.findByDiscussAndUser(discuss,user,pa);
}


@GetMapping
("/findOne")
public Object findOne(@RequestParam(name = "Object") Object Object){
  return replydao.findOne(Object);
}


@GetMapping
("/save")
public Object save(@RequestParam(name = "Object") Object Object){
  return replydao.save(Object);
}


@GetMapping
("/delete")
public Object delete(@RequestParam(name = "Object") Object Object){
  return replydao.delete(Object);
}


}