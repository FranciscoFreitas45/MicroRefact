package org.gliderwiki.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.gliderwiki.Interface.RequestManager;
public class RequestManagerImpl implements RequestManager{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://0";


}