package pl.edu.wat.wcy.pz.restaurantServer.Request;
import pl.edu.wat.wcy.pz.restaurantServer.DTO.Dish;
public interface DishRequest {

   public Dish getDishId(Long dishIdv2);
   public void setDishId(Dish dishId,Long dishIdv2);
}