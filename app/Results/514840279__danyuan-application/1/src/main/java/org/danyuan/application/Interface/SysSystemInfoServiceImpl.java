package org.danyuan.application.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.danyuan.application.Interface.SysSystemInfoService;
public class SysSystemInfoServiceImpl implements SysSystemInfoService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://2";


}