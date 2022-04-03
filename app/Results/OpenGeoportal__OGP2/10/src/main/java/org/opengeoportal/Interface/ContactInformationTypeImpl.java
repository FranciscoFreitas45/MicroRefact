package org.opengeoportal.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.opengeoportal.Interface.ContactInformationType;
public class ContactInformationTypeImpl implements ContactInformationType{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://16";


}