package com.lingxiang2014.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.lingxiang2014.Interface.PluginService;
public class PluginServiceImpl implements PluginService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://10";


}