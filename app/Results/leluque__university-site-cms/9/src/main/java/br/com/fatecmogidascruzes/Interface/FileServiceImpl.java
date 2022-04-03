package br.com.fatecmogidascruzes.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import br.com.fatecmogidascruzes.Interface.FileService;
public class FileServiceImpl implements FileService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://7";


public Object removeByKey(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/removeByKey"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public File saveImage(MultipartFile multipartFile,String alternativeDescription){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/saveImage"))
    .queryParam("multipartFile",multipartFile)
    .queryParam("alternativeDescription",alternativeDescription)
;  File aux = restTemplate.getForObject(builder.toUriString(), File.class);

 return aux;
}


public File saveFile(MultipartFile file){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/saveFile"))
    .queryParam("file",file)
;  File aux = restTemplate.getForObject(builder.toUriString(), File.class);

 return aux;
}


}