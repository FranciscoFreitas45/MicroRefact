package cn.gson.oasys.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class NoteController {

 private NoteDao notedao;


@PutMapping
("/setReceiver/{id}")
public void setReceiver(@PathVariable(name = "id") Long id,@RequestParam(name = "receiver") String receiver){
 notedao.setReceiver(id,receiver);
}


@PutMapping
("/setCreatemanId/{id}")
public void setCreatemanId(@PathVariable(name = "id") Long id,@RequestParam(name = "createmanId") Long createmanId){
 notedao.setCreatemanId(id,createmanId);
}


@PutMapping
("/setUserss/{id}")
public void setUserss(@PathVariable(name = "id") Long id,@RequestParam(name = "userss") Set<User> userss){
 notedao.setUserss(id,userss);
}


}