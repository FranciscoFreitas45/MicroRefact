package org.opengeoportal.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.opengeoportal.Interface.LayerRequest;
public class LayerRequestImpl implements LayerRequest{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://5";


public void setMetadata(Boolean metadata){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setMetadata"))
    .queryParam("metadata",metadata)
;
  restTemplate.put(builder.toUriString(), null);
}


public SolrRecord getLayerInfo(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getLayerInfo"))
;  SolrRecord aux = restTemplate.getForObject(builder.toUriString(), SolrRecord.class);

 return aux;
}


public BoundingBox getRequestedBounds(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getRequestedBounds"))
;  BoundingBox aux = restTemplate.getForObject(builder.toUriString(), BoundingBox.class);

 return aux;
}


}