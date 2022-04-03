package com.jepescados.vendasapi.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.jepescados.vendasapi.Interface.DepartamentoDto;
public class DepartamentoDtoImpl implements DepartamentoDto{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://4";


}