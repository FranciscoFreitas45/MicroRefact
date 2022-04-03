package kr.ac.sejong.api.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import kr.ac.sejong.api.Interface.UploadRepository;
public class UploadRepositoryImpl implements UploadRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://1";


public List<Upload> findByUser(User user){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByUser"))
    .queryParam("user",user)
;  List<Upload> aux = restTemplate.getForObject(builder.toUriString(), List<Upload>.class);

 return aux;
}


}