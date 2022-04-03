package com.wxcrm.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.wxcrm.Interface.IWeixinService;
public class IWeixinServiceImpl implements IWeixinService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://4";


}