package run.halo.app.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class CommentBlackListServiceController {

 private CommentBlackListService commentblacklistservice;


@GetMapping
("/commentsBanStatus")
public CommentViolationTypeEnum commentsBanStatus(@RequestParam(name = "ipAddress") String ipAddress){
  return commentblacklistservice.commentsBanStatus(ipAddress);
}


@GetMapping
("/listAll")
public Object listAll(@RequestParam(name = "Object") Object Object){
  return commentblacklistservice.listAll(Object);
}


@GetMapping
("/createInBatch")
public Object createInBatch(@RequestParam(name = "Object") Object Object){
  return commentblacklistservice.createInBatch(Object);
}


}