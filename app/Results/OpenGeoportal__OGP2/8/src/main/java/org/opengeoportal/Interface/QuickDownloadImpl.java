package org.opengeoportal.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.opengeoportal.Interface.QuickDownload;
public class QuickDownloadImpl implements QuickDownload{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://9";


public File downloadZipFile(String layerId,BoundingBox bounds){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/downloadZipFile"))
    .queryParam("layerId",layerId)
    .queryParam("bounds",bounds)
;  File aux = restTemplate.getForObject(builder.toUriString(), File.class);

 return aux;
}


}