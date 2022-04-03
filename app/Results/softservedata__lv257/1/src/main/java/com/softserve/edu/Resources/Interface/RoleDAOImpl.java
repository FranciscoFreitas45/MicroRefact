package com.softserve.edu.Resources.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.softserve.edu.Resources.Interface.RoleDAO;
public class RoleDAOImpl implements RoleDAO{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://0";


}