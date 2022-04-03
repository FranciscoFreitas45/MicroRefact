package com.poseidon.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.poseidon.Interface.CustomerVO;
public class CustomerVOImpl implements CustomerVO{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://1";


}