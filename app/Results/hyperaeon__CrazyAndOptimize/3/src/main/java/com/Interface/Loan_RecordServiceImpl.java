package com.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.Interface.Loan_RecordService;
public class Loan_RecordServiceImpl implements Loan_RecordService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://2";


}