package pl.szymanski.sharelibrary.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import pl.szymanski.sharelibrary.DTO.Coordinates;
import pl.szymanski.sharelibrary.Request.CoordinatesRequest;
public class CoordinatesRequestImpl implements CoordinatesRequest{

 private RestTemplate restTemplate = new RestTemplate();;


}