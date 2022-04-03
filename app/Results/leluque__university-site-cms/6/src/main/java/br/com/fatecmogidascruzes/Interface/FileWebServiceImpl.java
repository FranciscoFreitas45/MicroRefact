package br.com.fatecmogidascruzes.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import br.com.fatecmogidascruzes.Interface.FileWebService;
public class FileWebServiceImpl implements FileWebService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://7";


public FileDTO getImage(UUID fileHash,Integer width,Integer height){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getImage"))
    .queryParam("fileHash",fileHash)
    .queryParam("width",width)
    .queryParam("height",height)
;  FileDTO aux = restTemplate.getForObject(builder.toUriString(), FileDTO.class);

 return aux;
}


}