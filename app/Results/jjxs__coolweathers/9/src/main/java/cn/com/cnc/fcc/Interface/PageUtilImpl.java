package cn.com.cnc.fcc.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import cn.com.cnc.fcc.Interface.PageUtil;
public class PageUtilImpl implements PageUtil{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://6";


public Page<T> listToPage(List<T> list,Pageable pageable){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/listToPage"))
    .queryParam("list",list)
    .queryParam("pageable",pageable)
;  Page<T> aux = restTemplate.getForObject(builder.toUriString(), Page<T>.class);

 return aux;
}


}