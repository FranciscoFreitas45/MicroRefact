package cn.gson.oasys.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class UserController {

 private User user;


@PutMapping
("/setIsLock/{id}")
public void setIsLock(@PathVariable(name = "id") Long id,@RequestParam(name = "isLock") Integer isLock){
 userdao.setIsLock(id,isLock);
}


@PutMapping
("/setUserTel/{id}")
public void setUserTel(@PathVariable(name = "id") Long id,@RequestParam(name = "userTel") String userTel){
 userdao.setUserTel(id,userTel);
}


@PutMapping
("/setRealName/{id}")
public void setRealName(@PathVariable(name = "id") Long id,@RequestParam(name = "realName") String realName){
 userdao.setRealName(id,realName);
}


@PutMapping
("/setEamil/{id}")
public void setEamil(@PathVariable(name = "id") Long id,@RequestParam(name = "eamil") String eamil){
 userdao.setEamil(id,eamil);
}


@PutMapping
("/setAddress/{id}")
public void setAddress(@PathVariable(name = "id") Long id,@RequestParam(name = "address") String address){
 userdao.setAddress(id,address);
}


@PutMapping
("/setUserEdu/{id}")
public void setUserEdu(@PathVariable(name = "id") Long id,@RequestParam(name = "userEdu") String userEdu){
 userdao.setUserEdu(id,userEdu);
}


@PutMapping
("/setSchool/{id}")
public void setSchool(@PathVariable(name = "id") Long id,@RequestParam(name = "school") String school){
 userdao.setSchool(id,school);
}


@PutMapping
("/setIdCard/{id}")
public void setIdCard(@PathVariable(name = "id") Long id,@RequestParam(name = "idCard") String idCard){
 userdao.setIdCard(id,idCard);
}


@PutMapping
("/setBank/{id}")
public void setBank(@PathVariable(name = "id") Long id,@RequestParam(name = "bank") String bank){
 userdao.setBank(id,bank);
}


@PutMapping
("/setThemeSkin/{id}")
public void setThemeSkin(@PathVariable(name = "id") Long id,@RequestParam(name = "themeSkin") String themeSkin){
 userdao.setThemeSkin(id,themeSkin);
}


@PutMapping
("/setSalary/{id}")
public void setSalary(@PathVariable(name = "id") Long id,@RequestParam(name = "salary") String salary){
 userdao.setSalary(id,salary);
}


@PutMapping
("/setFatherId/{id}")
public void setFatherId(@PathVariable(name = "id") Long id,@RequestParam(name = "fatherId") Long fatherId){
 userdao.setFatherId(id,fatherId);
}


@PutMapping
("/setPassword/{id}")
public void setPassword(@PathVariable(name = "id") Long id,@RequestParam(name = "password") String password){
 userdao.setPassword(id,password);
}


@PutMapping
("/setDept/{id}")
public void setDept(@PathVariable(name = "id") Long id,@RequestParam(name = "dept") Dept dept){
 userdao.setDept(id,dept);
}


@PutMapping
("/setRole/{id}")
public void setRole(@PathVariable(name = "id") Long id,@RequestParam(name = "role") Role role){
 userdao.setRole(id,role);
}


@PutMapping
("/setPosition/{id}")
public void setPosition(@PathVariable(name = "id") Long id,@RequestParam(name = "position") Position position){
 userdao.setPosition(id,position);
}


@PutMapping
("/setPinyin/{id}")
public void setPinyin(@PathVariable(name = "id") Long id,@RequestParam(name = "pinyin") String pinyin){
 userdao.setPinyin(id,pinyin);
}


@PutMapping
("/setSex/{id}")
public void setSex(@PathVariable(name = "id") Long id,@RequestParam(name = "sex") String sex){
 userdao.setSex(id,sex);
}


@PutMapping
("/setBirth/{id}")
public void setBirth(@PathVariable(name = "id") Long id,@RequestParam(name = "birth") Date birth){
 userdao.setBirth(id,birth);
}


@PutMapping
("/setUserSign/{id}")
public void setUserSign(@PathVariable(name = "id") Long id,@RequestParam(name = "userSign") String userSign){
 userdao.setUserSign(id,userSign);
}


@PutMapping
("/setImgPath/{id}")
public void setImgPath(@PathVariable(name = "id") Long id,@RequestParam(name = "imgPath") String imgPath){
 userdao.setImgPath(id,imgPath);
}


@PutMapping
("/setaSet/{id}")
public void setaSet(@PathVariable(name = "id") Long id,@RequestParam(name = "aSet") Set<Attends> aSet){
 userdao.setaSet(id,aSet);
}


}