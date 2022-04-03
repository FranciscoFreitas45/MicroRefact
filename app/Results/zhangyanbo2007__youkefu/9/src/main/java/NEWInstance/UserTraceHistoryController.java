package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class UserTraceHistoryController {

 private UserTraceHistory usertracehistory;

 private UserTraceHistory usertracehistory;


@PutMapping
("/setTitle")
public void setTitle(@RequestParam(name = "title") String title){
usertracehistory.setTitle(title);
}


@PutMapping
("/setUrl")
public void setUrl(@RequestParam(name = "url") String url){
usertracehistory.setUrl(url);
}


@PutMapping
("/setOrgi")
public void setOrgi(@RequestParam(name = "orgi") String orgi){
usertracehistory.setOrgi(orgi);
}


@PutMapping
("/setUpdatetime")
public void setUpdatetime(@RequestParam(name = "updatetime") Date updatetime){
usertracehistory.setUpdatetime(updatetime);
}


@PutMapping
("/setUsername")
public void setUsername(@RequestParam(name = "username") String username){
usertracehistory.setUsername(username);
}


}