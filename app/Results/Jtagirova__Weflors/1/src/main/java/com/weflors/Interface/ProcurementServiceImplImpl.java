package com.weflors.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.weflors.Interface.ProcurementServiceImpl;
public class ProcurementServiceImplImpl implements ProcurementServiceImpl{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://2";


}