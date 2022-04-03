package org.opengeoportal.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.opengeoportal.Interface.FeatureSourceToShape;
public class FeatureSourceToShapeImpl implements FeatureSourceToShape{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://9";


public FeatureSourceRetriever getFeatureSourceRetriever(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getFeatureSourceRetriever"))
;  FeatureSourceRetriever aux = restTemplate.getForObject(builder.toUriString(), FeatureSourceRetriever.class);

 return aux;
}


public void setFeatureCollectionBBox(Envelope bbox){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setFeatureCollectionBBox"))
    .queryParam("bbox",bbox)
;
  restTemplate.put(builder.toUriString(), null);
}


public Set<File> exportToShapefiles(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/exportToShapefiles"))
;  Set<File> aux = restTemplate.getForObject(builder.toUriString(), Set<File>.class);

 return aux;
}


}