package pl.edu.wat.wcy.pz.restaurantServer.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import pl.edu.wat.wcy.pz.restaurantServer.DTO.Dish;
import pl.edu.wat.wcy.pz.restaurantServer.Request.DishRequest;
  public class DishRequestImpl implements DishRequest {

   private RestTemplate restTemplate = new RestTemplate();


  public Dish getDishId(Long dishId0IRY){
   Dish aux = restTemplate.getForObject("http://4/BillPosition/{id}/Dish/getDishId",Dish.class,dishId0IRY);
  return aux;
  }


  public void setDishId(Dish dishId,Long dishId0IRY){
   restTemplate.put("http://4/BillPosition/{id}/Dish/setDishId",dishId,dishId0IRY);
   return ;
  }


 }

