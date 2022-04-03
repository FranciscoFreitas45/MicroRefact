package pl.edu.wat.wcy.pz.restaurantServer.repository;
 import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.wat.wcy.pz.restaurantServer.entity.Dish;
import javax.transaction.Transactional;
@Transactional
@Repository
public interface DishRepository extends JpaRepository<Dish, Long>{


public Dish getDishId(Long dishId0IRY);

public void setDishId(Long dishId0IRY,Dish dishId);

}