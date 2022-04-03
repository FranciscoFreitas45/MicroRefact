package com.jepescados.vendasapi.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.jepescados.vendasapi.Interface.UsuarioDto;
public class UsuarioDtoImpl implements UsuarioDto{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://0";


}