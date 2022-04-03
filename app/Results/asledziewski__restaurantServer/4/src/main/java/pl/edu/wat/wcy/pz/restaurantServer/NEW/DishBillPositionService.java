package pl.edu.wat.wcy.pz.restaurantServer.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import pl.edu.wat.wcy.pz.restaurantServer.repository.DishRepository;
import pl.edu.wat.wcy.pz.restaurantServer.entity.Dish;
 @Service
 public class DishBillPositionService {

 @Autowired
  private DishRepository dishrepository;


 public Dish getDishId(Long dishId0IRY){
 return dishrepository.getDishId(dishId0IRY);
  }


 public void setDishId(Long dishId0IRY,Dish dishId){
 dishrepository.setDishId(dishId0IRY,dishId);
  }


 }

 