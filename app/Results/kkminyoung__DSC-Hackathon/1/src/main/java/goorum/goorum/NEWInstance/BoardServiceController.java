package goorum.goorum.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class BoardServiceController {

 private BoardService boardservice;


@GetMapping
("/getListByMemberId")
public List<Boardlist> getListByMemberId(@RequestParam(name = "memberId") long memberId,@RequestParam(name = "page") int page,@RequestParam(name = "size") int size,@RequestParam(name = "mav") ModelAndView mav){
  return boardservice.getListByMemberId(memberId,page,size,mav);
}


@GetMapping
("/getBoardById")
public Board getBoardById(@RequestParam(name = "boardId") long boardId){
  return boardservice.getBoardById(boardId);
}


@GetMapping
("/getBoardListById")
public Boardlist getBoardListById(@RequestParam(name = "boardId") long boardId){
  return boardservice.getBoardListById(boardId);
}


}