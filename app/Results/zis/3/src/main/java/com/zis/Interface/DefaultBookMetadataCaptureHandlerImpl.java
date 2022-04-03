package com.zis.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.zis.Interface.DefaultBookMetadataCaptureHandler;
public class DefaultBookMetadataCaptureHandlerImpl implements DefaultBookMetadataCaptureHandler{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://8";


public BookMetadata captureDetailPage(String itemId,String bookMetadataSource){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/captureDetailPage"))
    .queryParam("itemId",itemId)
    .queryParam("bookMetadataSource",bookMetadataSource)
;  BookMetadata aux = restTemplate.getForObject(builder.toUriString(), BookMetadata.class);

 return aux;
}


public BookMetadata captureListPage(String keyword){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/captureListPage"))
    .queryParam("keyword",keyword)
;  BookMetadata aux = restTemplate.getForObject(builder.toUriString(), BookMetadata.class);

 return aux;
}


}