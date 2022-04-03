package goorum.goorum.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import goorum.goorum.Interface.BoardService;
public class BoardServiceImpl implements BoardService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://1";


public List<Boardlist> getListByMemberId(long memberId,int page,int size,ModelAndView mav){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getListByMemberId"))
    .queryParam("memberId",memberId)
    .queryParam("page",page)
    .queryParam("size",size)
    .queryParam("mav",mav)
;  List<Boardlist> aux = restTemplate.getForObject(builder.toUriString(), List<Boardlist>.class);

 return aux;
}


public Board getBoardById(long boardId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getBoardById"))
    .queryParam("boardId",boardId)
;  Board aux = restTemplate.getForObject(builder.toUriString(), Board.class);

 return aux;
}


public Boardlist getBoardListById(long boardId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getBoardListById"))
    .queryParam("boardId",boardId)
;  Boardlist aux = restTemplate.getForObject(builder.toUriString(), Boardlist.class);

 return aux;
}


}