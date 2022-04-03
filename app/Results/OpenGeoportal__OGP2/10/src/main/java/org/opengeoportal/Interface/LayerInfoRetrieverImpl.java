package org.opengeoportal.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.opengeoportal.Interface.LayerInfoRetriever;
public class LayerInfoRetrieverImpl implements LayerInfoRetriever{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://3";


public List<SolrRecord> fetchAllLayerInfo(Set<String> layerIds){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/fetchAllLayerInfo"))
    .queryParam("layerIds",layerIds)
;  List<SolrRecord> aux = restTemplate.getForObject(builder.toUriString(), List<SolrRecord>.class);

 return aux;
}


public SolrServer getSolrServer(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getSolrServer"))
;  SolrServer aux = restTemplate.getForObject(builder.toUriString(), SolrServer.class);

 return aux;
}


public Object query(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/query"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


}