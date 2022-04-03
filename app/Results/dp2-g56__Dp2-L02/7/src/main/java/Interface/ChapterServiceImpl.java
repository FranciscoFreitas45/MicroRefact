package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import Interface.ChapterService;
public class ChapterServiceImpl implements ChapterService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://6";


public void deleteAccountChapter(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/deleteAccountChapter"))
;
  restTemplate.put(builder.toUriString(), null);
}


}