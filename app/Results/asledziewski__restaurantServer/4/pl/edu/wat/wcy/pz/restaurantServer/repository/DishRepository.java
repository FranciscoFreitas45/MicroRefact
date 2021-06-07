import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.wat.wcy.pz.restaurantServer.entity.Dish;
import javax.transaction.Transactional;
@Transactional
@Repository
public interface DishRepository extends JpaRepository<Dish, Long> {


public Dish getDishId(Long dishIdv2)

public void setDishId(Long dishIdv2,Dish dishId)

}