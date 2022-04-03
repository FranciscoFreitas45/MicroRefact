package org.opengeoportal.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.opengeoportal.Interface.LayerInfoRetriever;
public class LayerInfoRetrieverImpl implements LayerInfoRetriever{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://3";


public SolrRecord getAllLayerInfo(String layerId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getAllLayerInfo"))
    .queryParam("layerId",layerId)
;  SolrRecord aux = restTemplate.getForObject(builder.toUriString(), SolrRecord.class);

 return aux;
}


}