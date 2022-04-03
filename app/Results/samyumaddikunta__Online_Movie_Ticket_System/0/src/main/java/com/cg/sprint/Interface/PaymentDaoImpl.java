package com.cg.sprint.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.cg.sprint.Interface.PaymentDao;
public class PaymentDaoImpl implements PaymentDao{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://1";


}