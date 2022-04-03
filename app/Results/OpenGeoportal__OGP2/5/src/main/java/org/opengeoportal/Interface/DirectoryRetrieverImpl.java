package org.opengeoportal.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.opengeoportal.Interface.DirectoryRetriever;
public class DirectoryRetrieverImpl implements DirectoryRetriever{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://9";


public File getDownloadDirectory(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getDownloadDirectory"))
;  File aux = restTemplate.getForObject(builder.toUriString(), File.class);

 return aux;
}


}