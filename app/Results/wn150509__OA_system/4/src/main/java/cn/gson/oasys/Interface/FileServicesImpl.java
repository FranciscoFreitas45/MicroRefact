package cn.gson.oasys.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import cn.gson.oasys.Interface.FileServices;
public class FileServicesImpl implements FileServices{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://2";


public Object savefile(MultipartFile file,User user,FilePath nowpath,boolean isfile){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/savefile"))
    .queryParam("file",file)
    .queryParam("user",user)
    .queryParam("nowpath",nowpath)
    .queryParam("isfile",isfile)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public Integer updateatt(MultipartFile file,User user,FilePath nowpath,long attid){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/updateatt"))
    .queryParam("file",file)
    .queryParam("user",user)
    .queryParam("nowpath",nowpath)
    .queryParam("attid",attid)
;  Integer aux = restTemplate.getForObject(builder.toUriString(), Integer.class);

 return aux;
}


}