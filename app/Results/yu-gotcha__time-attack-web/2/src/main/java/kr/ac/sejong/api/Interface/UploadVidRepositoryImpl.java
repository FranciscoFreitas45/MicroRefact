package kr.ac.sejong.api.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import kr.ac.sejong.api.Interface.UploadVidRepository;
public class UploadVidRepositoryImpl implements UploadVidRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://3";


public List<UploadVid> findByVidUpUser(User user){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByVidUpUser"))
    .queryParam("user",user)
;  List<UploadVid> aux = restTemplate.getForObject(builder.toUriString(), List<UploadVid>.class);

 return aux;
}


public Object size(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/size"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


}