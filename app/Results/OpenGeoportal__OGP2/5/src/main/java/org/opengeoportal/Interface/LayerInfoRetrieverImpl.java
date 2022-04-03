package org.opengeoportal.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.opengeoportal.Interface.LayerInfoRetriever;
public class LayerInfoRetrieverImpl implements LayerInfoRetriever{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://3";


public List<SolrRecord> fetchAllowedRecords(Set<String> layerIdSet){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/fetchAllowedRecords"))
    .queryParam("layerIdSet",layerIdSet)
;  List<SolrRecord> aux = restTemplate.getForObject(builder.toUriString(), List<SolrRecord>.class);

 return aux;
}


}