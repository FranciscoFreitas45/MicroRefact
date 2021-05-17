import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
public class HstServerInfoRepositoryImpl implements HstServerInfoRepository{

 private RestTemplate restTemplate;

  String url = "http://localhost:teste";


public List<HstServer> findByNodeId(String nodeId,int hostSlaveFlag){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("findByNodeId"))
    .queryParam("nodeId",nodeId)
    .queryParam("hostSlaveFlag",hostSlaveFlag);
  List<HstServer> aux = restTemplate.getForObject(builder.toUriString(), List<HstServer>.class)

 return aux;
}


public List<HstServer> findByNodeId(String nodeId,int hostSlaveFlag){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("findByNodeId"))
    .queryParam("nodeId",nodeId)
    .queryParam("hostSlaveFlag",hostSlaveFlag);
  List<HstServer> aux = restTemplate.getForObject(builder.toUriString(), List<HstServer>.class)

 return aux;
}


public List<HstServer> findByNodeId(String nodeId,int hostSlaveFlag){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("findByNodeId"))
    .queryParam("nodeId",nodeId)
    .queryParam("hostSlaveFlag",hostSlaveFlag);
  List<HstServer> aux = restTemplate.getForObject(builder.toUriString(), List<HstServer>.class)

 return aux;
}


public List<HstServer> findByNodeId(String nodeId,int hostSlaveFlag){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("findByNodeId"))
    .queryParam("nodeId",nodeId)
    .queryParam("hostSlaveFlag",hostSlaveFlag);
  List<HstServer> aux = restTemplate.getForObject(builder.toUriString(), List<HstServer>.class)

 return aux;
}


public List<HstServer> findHostServer(int hostSlaveFlag){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("findHostServer"))
    .queryParam("hostSlaveFlag",hostSlaveFlag);
  List<HstServer> aux = restTemplate.getForObject(builder.toUriString(), List<HstServer>.class)

 return aux;
}


public List<HstServer> findHostServer(int hostSlaveFlag){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("findHostServer"))
    .queryParam("hostSlaveFlag",hostSlaveFlag);
  List<HstServer> aux = restTemplate.getForObject(builder.toUriString(), List<HstServer>.class)

 return aux;
}


}