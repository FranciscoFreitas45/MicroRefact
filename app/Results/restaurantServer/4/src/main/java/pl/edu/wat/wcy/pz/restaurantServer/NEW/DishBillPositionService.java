package pl.edu.wat.wcy.pz.restaurantServer.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import pl.edu.wat.wcy.pz.restaurantServer.repository.DishRepository;
import pl.edu.wat.wcy.pz.restaurantServer.entity.Dish;
@Service
public class DishBillPositionService {

@Autowired
 private DishRepository dishrepository;


public Dish getDishId(Long dishIdv2){
return dishrepository.getDishId(dishIdv2);
}


public void setDishId(Long dishIdv2,Dish dishId){
dishrepository.setDishId(dishIdv2,dishId);
}


}