package com.hmm.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.hmm.Interface.InDetailedRepository;
public class InDetailedRepositoryImpl implements InDetailedRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://9";


public Page<InStorageDetailedDTO> findInStorageDetailedByInAll(InStorage inStorageId,Pageable pageable){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findInStorageDetailedByInAll"))
    .queryParam("inStorageId",inStorageId)
    .queryParam("pageable",pageable)
;  Page<InStorageDetailedDTO> aux = restTemplate.getForObject(builder.toUriString(), Page<InStorageDetailedDTO>.class);

 return aux;
}


public Object findById(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findById"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public Object save(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/save"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


}