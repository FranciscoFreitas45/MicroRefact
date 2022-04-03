package com.ec.survey.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.ec.survey.Interface.XmlExportCreator;
public class XmlExportCreatorImpl implements XmlExportCreator{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://4";


}