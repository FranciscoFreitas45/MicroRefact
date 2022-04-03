package com.qidian.hcm.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.qidian.hcm.Interface.AttachmentRepository;
public class AttachmentRepositoryImpl implements AttachmentRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://8";


public Object save(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/save"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public Optional<Attachment> findByFileId(Long fileId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByFileId"))
    .queryParam("fileId",fileId)
;  Optional<Attachment> aux = restTemplate.getForObject(builder.toUriString(), Optional<Attachment>.class);

 return aux;
}


}