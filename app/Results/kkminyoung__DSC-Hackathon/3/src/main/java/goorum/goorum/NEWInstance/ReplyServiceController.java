package goorum.goorum.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ReplyServiceController {

 private ReplyService replyservice;


@GetMapping
("/getRepliesByBoardId")
public List<Replylist> getRepliesByBoardId(@RequestParam(name = "boardId") long boardId){
  return replyservice.getRepliesByBoardId(boardId);
}


@GetMapping
("/writeReply")
public boolean writeReply(@RequestParam(name = "boardId") long boardId,@RequestParam(name = "parent") long parent,@RequestParam(name = "content") String content,@RequestParam(name = "member") Member member){
  return replyservice.writeReply(boardId,parent,content,member);
}


@GetMapping
("/deleteReply")
public boolean deleteReply(@RequestParam(name = "replyId") long replyId,@RequestParam(name = "parent") long parent,@RequestParam(name = "member") Member member){
  return replyservice.deleteReply(replyId,parent,member);
}


}