package org.sdrc.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.sdrc.Interface.SourceRepository;
public class SourceRepositoryImpl implements SourceRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://16";


public List<UtIndicatorClassificationsEn> findByIUS_Nid(Integer iusNid){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByIUS_Nid"))
    .queryParam("iusNid",iusNid)
;  List<UtIndicatorClassificationsEn> aux = restTemplate.getForObject(builder.toUriString(), List<UtIndicatorClassificationsEn>.class);

 return aux;
}


public List<UtIndicatorClassificationsEn> findByIUSandLevel_Nid(Integer iusNid,Integer levelNid){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByIUSandLevel_Nid"))
    .queryParam("iusNid",iusNid)
    .queryParam("levelNid",levelNid)
;  List<UtIndicatorClassificationsEn> aux = restTemplate.getForObject(builder.toUriString(), List<UtIndicatorClassificationsEn>.class);

 return aux;
}


}