package com.jepescados.vendasapi.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.jepescados.vendasapi.Interface.EnderecoDto;
public class EnderecoDtoImpl implements EnderecoDto{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://2";


}