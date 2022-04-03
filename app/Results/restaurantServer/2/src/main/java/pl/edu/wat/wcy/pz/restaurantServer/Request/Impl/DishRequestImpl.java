package pl.edu.wat.wcy.pz.restaurantServer.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import pl.edu.wat.wcy.pz.restaurantServer.DTO.Dish;
import pl.edu.wat.wcy.pz.restaurantServer.Request.DishRequest;
public class DishRequestImpl implements DishRequest{

 private RestTemplate restTemplate = new RestTemplate();;


public Dish getDishId(Long dishIdv2){
 System.out.println(this.restTemplate);
 System.out.println(dishIdv2);       
 Dish aux = restTemplate.getForObject("http://localhost:8083/BillPosition/{id}/Dish/getDishId",Dish.class,dishIdv2);
return aux;
}


public void setDishId(Dish dishId,Long dishIdv2){
 restTemplate.put("http://localhost:8083/BillPosition/{id}/Dish/setDishId",dishId,dishIdv2);
 return ;
}


}