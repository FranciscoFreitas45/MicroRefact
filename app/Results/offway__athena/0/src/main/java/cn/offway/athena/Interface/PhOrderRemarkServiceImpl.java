package cn.offway.athena.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import cn.offway.athena.Interface.PhOrderRemarkService;
public class PhOrderRemarkServiceImpl implements PhOrderRemarkService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://8";


public List<PhOrderRemark> save(List<PhOrderRemark> entities){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/save"))
    .queryParam("entities",entities)
;  List<PhOrderRemark> aux = restTemplate.getForObject(builder.toUriString(), List<PhOrderRemark>.class);

 return aux;
}


public Page<PhOrderRemark> findAllByPage(String id,Pageable page){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findAllByPage"))
    .queryParam("id",id)
    .queryParam("page",page)
;  Page<PhOrderRemark> aux = restTemplate.getForObject(builder.toUriString(), Page<PhOrderRemark>.class);

 return aux;
}


public void delete(Long id){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/delete"))
    .queryParam("id",id)
;
  restTemplate.put(builder.toUriString(), null);
}


}