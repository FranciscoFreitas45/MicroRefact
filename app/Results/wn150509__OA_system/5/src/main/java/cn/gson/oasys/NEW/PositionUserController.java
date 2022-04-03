package cn.gson.oasys.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import cn.gson.oasys.model.entity.user.Position;
@RestController
@CrossOrigin
public class PositionUserController {

@Autowired
 private PositionUserService positionuserservice;


@PutMapping
("/User/{id}/Position/setPosition")
public void setPosition(@PathVariable(name="id") Long id,@RequestBody Position position){
positionuserservice.setPosition(id,position);
}


@GetMapping
("/User/{id}/Position/getPosition")
public Position getPosition(@PathVariable(name="id") Long id){
return positionuserservice.getPosition(id);
}


}