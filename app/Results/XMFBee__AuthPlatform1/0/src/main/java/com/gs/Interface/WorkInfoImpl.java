package com.gs.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.gs.Interface.WorkInfo;
public class WorkInfoImpl implements WorkInfo{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://5";


}