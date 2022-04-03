package org.opengeoportal.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.opengeoportal.Interface.LegendGraphic;
public class LegendGraphicImpl implements LegendGraphic{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://0";


}