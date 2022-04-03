package org.opengeoportal.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.opengeoportal.Interface.MetadataRetriever;
public class MetadataRetrieverImpl implements MetadataRetriever{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://9";


public File getXMLFile(String metadataFileName,File xmlFile){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getXMLFile"))
    .queryParam("metadataFileName",metadataFileName)
    .queryParam("xmlFile",xmlFile)
;  File aux = restTemplate.getForObject(builder.toUriString(), File.class);

 return aux;
}


}