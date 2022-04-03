package org.sdrc.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.sdrc.Interface.DashboardController;
public class DashboardControllerImpl implements DashboardController{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://2";


}