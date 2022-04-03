package org.sdrc.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.sdrc.Interface.TemplateUploadMetaRepository;
public class TemplateUploadMetaRepositoryImpl implements TemplateUploadMetaRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://21";


public TemplateUploadMeta save(TemplateUploadMeta templateUploadMeta){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/save"))
    .queryParam("templateUploadMeta",templateUploadMeta)
;  TemplateUploadMeta aux = restTemplate.getForObject(builder.toUriString(), TemplateUploadMeta.class);

 return aux;
}


}