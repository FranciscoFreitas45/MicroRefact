package Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
public class UserRequestImpl implements UserRequest{

@Autowired
 private RestTemplate restTemplate;


public User getUser(Long Long){
 User aux = restTemplate.getForObject("http://4/PersistentToken/{id}/User/getUser",User.class,Long);
return aux;
}


public void setUser(User user,Long Long){
 restTemplate.put("http://4/PersistentToken/{id}/User/setUser",user,Long);
 return ;
}


}