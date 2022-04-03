package org.jugbd.mnet.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.jugbd.mnet.Interface.VitalDao;
public class VitalDaoImpl implements VitalDao{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://8";


}