import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
public class HostOrSlaveRequestResourceImpl implements HostOrSlaveRequestResource{

 private RestTemplate restTemplate;

  String url = "http://localhost:teste";


public String saveInfomation(List<HstServer> hstServers,String localUrl,String localNode,String hostNodeInfo,List<HstServer> postHstServers){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("saveInfomation"))
    .queryParam("hstServers",hstServers)
    .queryParam("localUrl",localUrl)
    .queryParam("localNode",localNode)
    .queryParam("hostNodeInfo",hostNodeInfo)
    .queryParam("postHstServers",postHstServers);
  String aux = restTemplate.getForObject(builder.toUriString(), String.class)

 return aux;
}


public String saveInfomation(List<HstServer> hstServers,String localUrl,String localNode,String hostNodeInfo,List<HstServer> postHstServers){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("saveInfomation"))
    .queryParam("hstServers",hstServers)
    .queryParam("localUrl",localUrl)
    .queryParam("localNode",localNode)
    .queryParam("hostNodeInfo",hostNodeInfo)
    .queryParam("postHstServers",postHstServers);
  String aux = restTemplate.getForObject(builder.toUriString(), String.class)

 return aux;
}


public String saveInfomation(List<HstServer> hstServers,String localUrl,String localNode,String hostNodeInfo,List<HstServer> postHstServers){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("saveInfomation"))
    .queryParam("hstServers",hstServers)
    .queryParam("localUrl",localUrl)
    .queryParam("localNode",localNode)
    .queryParam("hostNodeInfo",hostNodeInfo)
    .queryParam("postHstServers",postHstServers);
  String aux = restTemplate.getForObject(builder.toUriString(), String.class)

 return aux;
}


}