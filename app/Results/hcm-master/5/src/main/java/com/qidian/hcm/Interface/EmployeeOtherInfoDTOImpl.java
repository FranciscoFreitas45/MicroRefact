package com.qidian.hcm.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.qidian.hcm.Interface.EmployeeOtherInfoDTO;
public class EmployeeOtherInfoDTOImpl implements EmployeeOtherInfoDTO{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://9";


}