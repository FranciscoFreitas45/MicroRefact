package org.danyuan.application.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.danyuan.application.Interface.SysUserEvaluateService;
public class SysUserEvaluateServiceImpl implements SysUserEvaluateService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://2";


}