package sn.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import sn.Interface.PostService;
public class PostServiceImpl implements PostService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://6";


public List<Post> findAllByPersonId(long personId,int offset,int itemPerPage){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findAllByPersonId"))
    .queryParam("personId",personId)
    .queryParam("offset",offset)
    .queryParam("itemPerPage",itemPerPage)
;  List<Post> aux = restTemplate.getForObject(builder.toUriString(), List<Post>.class);

 return aux;
}


public WallPostResponse getExistsWallPost(Post post,PersonResponse author,List<CommentResponse> comments){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getExistsWallPost"))
    .queryParam("post",post)
    .queryParam("author",author)
    .queryParam("comments",comments)
;  WallPostResponse aux = restTemplate.getForObject(builder.toUriString(), WallPostResponse.class);

 return aux;
}


public int getTotalCountPostsByPersonId(long personId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getTotalCountPostsByPersonId"))
    .queryParam("personId",personId)
;  int aux = restTemplate.getForObject(builder.toUriString(), int.class);

 return aux;
}


public Post addPost(Person author,String title,String text,LocalDateTime postTime){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/addPost"))
    .queryParam("author",author)
    .queryParam("title",title)
    .queryParam("text",text)
    .queryParam("postTime",postTime)
;  Post aux = restTemplate.getForObject(builder.toUriString(), Post.class);

 return aux;
}


public WallPostResponse createNewWallPost(Post post,PersonResponse author){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/createNewWallPost"))
    .queryParam("post",post)
    .queryParam("author",author)
;  WallPostResponse aux = restTemplate.getForObject(builder.toUriString(), WallPostResponse.class);

 return aux;
}


}