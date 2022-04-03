package com.ushahidi.swiftriver.core.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.ushahidi.swiftriver.core.Interface.AccountDTO;
public class AccountDTOImpl implements AccountDTO{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://1";


}