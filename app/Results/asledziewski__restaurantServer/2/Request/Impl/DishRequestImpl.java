import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
public class DishRequestImpl implements DishRequest{

@Autowired
 private RestTemplate restTemplate;


public Dish getDishId(Long dishIdv2){
 Dish aux = restTemplate.getForObject('http://4/BillPosition/{id}/Dish/getDishId',Dish.class,dishIdv2);
return aux;
}


public void setDishId(Dish dishId,Long dishIdv2){
 restTemplate.put('http://4/BillPosition/{id}/Dish/setDishId',dishId,dishIdv2);
 return ;
}


}