package org.danyuan.application.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.danyuan.application.Interface.MultiDatasourceConfig;
public class MultiDatasourceConfigImpl implements MultiDatasourceConfig{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://4";


public Connection getConnection(String uuid){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getConnection"))
    .queryParam("uuid",uuid)
;  Connection aux = restTemplate.getForObject(builder.toUriString(), Connection.class);

 return aux;
}


}