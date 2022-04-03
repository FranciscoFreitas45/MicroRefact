package run.halo.app.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import run.halo.app.Interface.PostService;
public class PostServiceImpl implements PostService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://3";


public PostDetailVO importMarkdown(String markdown,String filename){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/importMarkdown"))
    .queryParam("markdown",markdown)
    .queryParam("filename",filename)
;  PostDetailVO aux = restTemplate.getForObject(builder.toUriString(), PostDetailVO.class);

 return aux;
}


public Object listAll(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/listAll"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public Object createInBatch(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/createInBatch"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public List<PostMarkdownVO> listPostMarkdowns(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/listPostMarkdowns"))
;  List<PostMarkdownVO> aux = restTemplate.getForObject(builder.toUriString(), List<PostMarkdownVO>.class);

 return aux;
}


}