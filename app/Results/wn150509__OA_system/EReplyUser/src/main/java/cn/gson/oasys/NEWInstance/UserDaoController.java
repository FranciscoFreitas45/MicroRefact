package cn.gson.oasys.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class UserDaoController {

 private UserDao userdao;


@GetMapping
("/findOne")
public Object findOne(@RequestParam(name = "Object") Object Object){
  return userdao.findOne(Object);
}


@GetMapping
("/findAll")
public Object findAll(@RequestParam(name = "Object") Object Object){
  return userdao.findAll(Object);
}


@GetMapping
("/findUsers")
public Page<User> findUsers(@RequestParam(name = "baseKey") String baseKey,@RequestParam(name = "baseKey2") String baseKey2,@RequestParam(name = "pa") Pageable pa){
  return userdao.findUsers(baseKey,baseKey2,pa);
}


@GetMapping
("/findSelectUsers")
public Page<User> findSelectUsers(@RequestParam(name = "baseKey") String baseKey,@RequestParam(name = "pinyinm") String pinyinm,@RequestParam(name = "pa") Pageable pa){
  return userdao.findSelectUsers(baseKey,pinyinm,pa);
}


@GetMapping
("/findByPinyinLike")
public Page<User> findByPinyinLike(@RequestParam(name = "pinyin") String pinyin,@RequestParam(name = "pa") Pageable pa){
  return userdao.findByPinyinLike(pinyin,pa);
}


@GetMapping
("/findByIsLock")
public Page<User> findByIsLock(@RequestParam(name = "isLock") Integer isLock,@RequestParam(name = "pa") Pageable pa){
  return userdao.findByIsLock(isLock,pa);
}


@GetMapping
("/findnamelike")
public Page<User> findnamelike(@RequestParam(name = "name") String name,@RequestParam(name = "pa") Pageable pa){
  return userdao.findnamelike(name,pa);
}


@GetMapping
("/save")
public Object save(@RequestParam(name = "Object") Object Object){
  return userdao.save(Object);
}


@GetMapping
("/findByUserName")
public User findByUserName(@RequestParam(name = "title") String title){
  return userdao.findByUserName(title);
}


@GetMapping
("/findid")
public User findid(@RequestParam(name = "name") String name){
  return userdao.findid(name);
}


@GetMapping
("/findbyUserNameLike")
public Page<User> findbyUserNameLike(@RequestParam(name = "name") String name,@RequestParam(name = "pa") Pageable pa){
  return userdao.findbyUserNameLike(name,pa);
}


@GetMapping
("/findbyFatherId")
public Page<User> findbyFatherId(@RequestParam(name = "name") String name,@RequestParam(name = "parentid") Long parentid,@RequestParam(name = "pa") Pageable pa){
  return userdao.findbyFatherId(name,parentid,pa);
}


@GetMapping
("/findByFatherId")
public Page<User> findByFatherId(@RequestParam(name = "parentid") Long parentid,@RequestParam(name = "pa") Pageable pa){
  return userdao.findByFatherId(parentid,pa);
}


@GetMapping
("/findpkId")
public Long findpkId(@RequestParam(name = "taskid") Long taskid,@RequestParam(name = "userid") Long userid){
  return userdao.findpkId(taskid,userid);
}


@GetMapping
("/findByDept")
public List<User> findByDept(@RequestParam(name = "dept") Dept dept){
  return userdao.findByDept(dept);
}


@GetMapping
("/findrole")
public List<User> findrole(@RequestParam(name = "lid") Long lid){
  return userdao.findrole(lid);
}


@GetMapping
("/findOneUser")
public User findOneUser(@RequestParam(name = "userName") String userName,@RequestParam(name = "password") String password){
  return userdao.findOneUser(userName,password);
}


}