package sn.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import sn.DTO.Person;
import sn.Request.PersonRequest;
public class PersonRequestImpl implements PersonRequest{

 private RestTemplate restTemplate = new RestTemplate();;


}