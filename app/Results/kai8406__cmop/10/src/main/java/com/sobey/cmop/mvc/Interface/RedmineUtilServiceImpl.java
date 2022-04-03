package com.sobey.cmop.mvc.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.sobey.cmop.mvc.Interface.RedmineUtilService;
public class RedmineUtilServiceImpl implements RedmineUtilService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://0";


}