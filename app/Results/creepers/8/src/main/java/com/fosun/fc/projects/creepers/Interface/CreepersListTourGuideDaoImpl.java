package com.fosun.fc.projects.creepers.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.fosun.fc.projects.creepers.Interface.CreepersListTourGuideDao;
public class CreepersListTourGuideDaoImpl implements CreepersListTourGuideDao{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://1";


public List<TCreepersListTourGuide> findByGuideNoAndCertNoOrCardNoAndCertNo(String guideNo,String certNo1,String cardNo,String certNo2){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByGuideNoAndCertNoOrCardNoAndCertNo"))
    .queryParam("guideNo",guideNo)
    .queryParam("certNo1",certNo1)
    .queryParam("cardNo",cardNo)
    .queryParam("certNo2",certNo2)
;  List<TCreepersListTourGuide> aux = restTemplate.getForObject(builder.toUriString(), List<TCreepersListTourGuide>.class);

 return aux;
}


public List<TCreepersListTourGuide> findByGuideNoOrCertNo(String guideNo,String cardNo){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByGuideNoOrCertNo"))
    .queryParam("guideNo",guideNo)
    .queryParam("cardNo",cardNo)
;  List<TCreepersListTourGuide> aux = restTemplate.getForObject(builder.toUriString(), List<TCreepersListTourGuide>.class);

 return aux;
}


public List<TCreepersListTourGuide> findByGuideNoAndCardNo(String guideNo,String cardNo){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByGuideNoAndCardNo"))
    .queryParam("guideNo",guideNo)
    .queryParam("cardNo",cardNo)
;  List<TCreepersListTourGuide> aux = restTemplate.getForObject(builder.toUriString(), List<TCreepersListTourGuide>.class);

 return aux;
}


public List<TCreepersListTourGuide> findByGuideNoAndCardNoAndCertNo(String guideNo,String cardNo,String certNo){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByGuideNoAndCardNoAndCertNo"))
    .queryParam("guideNo",guideNo)
    .queryParam("cardNo",cardNo)
    .queryParam("certNo",certNo)
;  List<TCreepersListTourGuide> aux = restTemplate.getForObject(builder.toUriString(), List<TCreepersListTourGuide>.class);

 return aux;
}


public Object findAll(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findAll"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public TCreepersListTourGuide findTop1ByGuideNoOrCardNo(String guideNo,String cardNo){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findTop1ByGuideNoOrCardNo"))
    .queryParam("guideNo",guideNo)
    .queryParam("cardNo",cardNo)
;  TCreepersListTourGuide aux = restTemplate.getForObject(builder.toUriString(), TCreepersListTourGuide.class);

 return aux;
}


public Object saveAndFlush(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/saveAndFlush"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


}