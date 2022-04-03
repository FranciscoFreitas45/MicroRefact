package org.gliderwiki.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class BoardServiceController {

 private BoardService boardservice;


@GetMapping
("/getRecentList")
public List<WeBbs> getRecentList(@RequestParam(name = "spaceIdx") int spaceIdx){
  return boardservice.getRecentList(spaceIdx);
}


}