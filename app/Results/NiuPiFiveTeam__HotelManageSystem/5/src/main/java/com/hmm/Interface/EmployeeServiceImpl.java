package com.hmm.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.hmm.Interface.EmployeeService;
public class EmployeeServiceImpl implements EmployeeService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://3";


}