package com.wxcrm.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.wxcrm.Interface.IAdminService;
public class IAdminServiceImpl implements IAdminService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://2";


}