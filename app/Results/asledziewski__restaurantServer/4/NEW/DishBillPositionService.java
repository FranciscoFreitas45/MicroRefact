import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
@AllArgsConstructor
@Service
public class DishBillPositionService {

 private DishRepository dishrepository;


public Dish getDishId(Long dishIdv2){
return dishrepository.getDishId(dishIdv2);
}


public void setDishId(Long dishIdv2,Dish dishId){
dishrepository.setDishId(dishIdv2,dishId);
}


}