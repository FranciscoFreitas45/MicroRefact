package cn.gson.oasys.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import cn.gson.oasys.model.entity.attendce.Attends;
@RestController
@CrossOrigin
public class AttendsUserController {

@Autowired
 private AttendsUserService attendsuserservice;


@GetMapping
("/User/{id}/Attends/getaSet")
public Set<Attends> getaSet(@PathVariable(name="id") Long userId){
return attendsuserservice.getaSet(userId);
}


@PutMapping
("/User/{id}/Attends/setaSet")
public void setaSet(@PathVariable(name="id") Long userId,@RequestBody Set<Attends> aSet){
attendsuserservice.setaSet(userId,aSet);
}


}