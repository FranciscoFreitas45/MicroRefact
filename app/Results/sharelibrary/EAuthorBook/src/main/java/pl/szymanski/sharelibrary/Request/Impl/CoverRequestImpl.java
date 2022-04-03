package pl.szymanski.sharelibrary.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import pl.szymanski.sharelibrary.DTO.Cover;
import pl.szymanski.sharelibrary.Request.CoverRequest;
public class CoverRequestImpl implements CoverRequest{

 private RestTemplate restTemplate = new RestTemplate();;


}