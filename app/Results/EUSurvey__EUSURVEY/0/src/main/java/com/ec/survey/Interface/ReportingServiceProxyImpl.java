package com.ec.survey.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.ec.survey.Interface.ReportingServiceProxy;
public class ReportingServiceProxyImpl implements ReportingServiceProxy{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://8";


}