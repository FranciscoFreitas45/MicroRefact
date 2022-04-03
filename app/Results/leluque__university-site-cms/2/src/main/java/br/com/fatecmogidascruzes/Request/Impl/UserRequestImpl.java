package br.com.fatecmogidascruzes.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import br.com.fatecmogidascruzes.DTO.User;
import br.com.fatecmogidascruzes.Request.UserRequest;
public class UserRequestImpl implements UserRequest{

 private RestTemplate restTemplate = new RestTemplate();;


}