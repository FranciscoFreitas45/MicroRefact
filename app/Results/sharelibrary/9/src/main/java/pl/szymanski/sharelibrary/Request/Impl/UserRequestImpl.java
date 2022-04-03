package pl.szymanski.sharelibrary.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import pl.szymanski.sharelibrary.DTO.User;
import pl.szymanski.sharelibrary.Request.UserRequest;
public class UserRequestImpl implements UserRequest{

 private RestTemplate restTemplate = new RestTemplate();;


}