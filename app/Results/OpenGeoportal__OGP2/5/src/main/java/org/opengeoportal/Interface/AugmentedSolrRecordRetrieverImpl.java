package org.opengeoportal.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.opengeoportal.Interface.AugmentedSolrRecordRetriever;
public class AugmentedSolrRecordRetrieverImpl implements AugmentedSolrRecordRetriever{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://3";


public AugmentedSolrRecord getOgcAugmentedSolrRecord(SolrRecord solrRecord){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getOgcAugmentedSolrRecord"))
    .queryParam("solrRecord",solrRecord)
;  AugmentedSolrRecord aux = restTemplate.getForObject(builder.toUriString(), AugmentedSolrRecord.class);

 return aux;
}


}