package com.ushahidi.swiftriver.core.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.ushahidi.swiftriver.core.Interface.LinkDao;
public class LinkDaoImpl implements LinkDao{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://3";


}