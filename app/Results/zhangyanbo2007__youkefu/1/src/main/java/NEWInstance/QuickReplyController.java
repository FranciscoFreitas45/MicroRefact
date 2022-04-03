package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class QuickReplyController {

 private QuickReply quickreply;

 private QuickReply quickreply;


@PutMapping
("/setOrgi")
public void setOrgi(@RequestParam(name = "orgi") String orgi){
quickreply.setOrgi(orgi);
}


@PutMapping
("/setCreater")
public void setCreater(@RequestParam(name = "creater") String creater){
quickreply.setCreater(creater);
}


@PutMapping
("/setCreatetime")
public void setCreatetime(@RequestParam(name = "createtime") Date createtime){
quickreply.setCreatetime(createtime);
}


@PutMapping
("/setType")
public void setType(@RequestParam(name = "type") String type){
quickreply.setType(type);
}


}