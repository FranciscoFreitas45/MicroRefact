package cn.com.cnc.fcc.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import cn.com.cnc.fcc.Interface.HostOrSlaveRequestResource;
public class HostOrSlaveRequestResourceImpl implements HostOrSlaveRequestResource{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://16";


public String saveInfomation(List<HstServer> hstServers,String localUrl,String localNode,String hostNodeInfo,List<HstServer> postHstServers){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/saveInfomation"))
    .queryParam("hstServers",hstServers)
    .queryParam("localUrl",localUrl)
    .queryParam("localNode",localNode)
    .queryParam("hostNodeInfo",hostNodeInfo)
    .queryParam("postHstServers",postHstServers)
;  String aux = restTemplate.getForObject(builder.toUriString(), String.class);

 return aux;
}


}