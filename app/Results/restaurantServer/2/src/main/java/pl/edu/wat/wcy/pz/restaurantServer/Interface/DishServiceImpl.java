package pl.edu.wat.wcy.pz.restaurantServer.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import pl.edu.wat.wcy.pz.restaurantServer.Interface.DishService;
public class DishServiceImpl implements DishService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://4";


}