package goorum.goorum.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class MemberLikeBoardServiceController {

 private MemberLikeBoardService memberlikeboardservice;


@GetMapping
("/isLike")
public boolean isLike(@RequestParam(name = "boardId") long boardId,@RequestParam(name = "memberId") long memberId){
  return memberlikeboardservice.isLike(boardId,memberId);
}


@PutMapping
("/dislike")
public void dislike(@RequestParam(name = "memberId") long memberId,@RequestParam(name = "boardId") long boardId){
memberlikeboardservice.dislike(memberId,boardId);
}


@GetMapping
("/like")
public boolean like(@RequestParam(name = "memberId") long memberId,@RequestParam(name = "boardId") long boardId){
  return memberlikeboardservice.like(memberId,boardId);
}


@GetMapping
("/likeBoard")
public List<MemberLikeBoard> likeBoard(@RequestParam(name = "memberId") long memberId){
  return memberlikeboardservice.likeBoard(memberId);
}


}