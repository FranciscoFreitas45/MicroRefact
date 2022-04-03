package org.opengeoportal.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.opengeoportal.Interface.OgcInfoRequest;
public class OgcInfoRequestImpl implements OgcInfoRequest{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://4";


}