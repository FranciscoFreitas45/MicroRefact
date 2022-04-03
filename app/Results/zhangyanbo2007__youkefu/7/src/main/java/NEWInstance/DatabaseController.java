package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class DatabaseController {

 private Database database;

 private Database database;


@PutMapping
("/setOrgi")
public void setOrgi(@RequestParam(name = "orgi") String orgi){
database.setOrgi(orgi);
}


@PutMapping
("/setCreatetime")
public void setCreatetime(@RequestParam(name = "createtime") Date createtime){
database.setCreatetime(createtime);
}


@PutMapping
("/setPassword")
public void setPassword(@RequestParam(name = "password") String password){
database.setPassword(password);
}


@PutMapping
("/setCreateuser")
public void setCreateuser(@RequestParam(name = "createuser") String createuser){
database.setCreateuser(createuser);
}


}