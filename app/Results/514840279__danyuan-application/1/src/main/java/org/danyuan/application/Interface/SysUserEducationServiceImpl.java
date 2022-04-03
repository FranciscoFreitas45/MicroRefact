package org.danyuan.application.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.danyuan.application.Interface.SysUserEducationService;
public class SysUserEducationServiceImpl implements SysUserEducationService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://2";


}