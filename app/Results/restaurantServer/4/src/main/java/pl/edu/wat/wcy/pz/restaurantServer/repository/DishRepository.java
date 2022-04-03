package pl.edu.wat.wcy.pz.restaurantServer.repository;
 import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.wat.wcy.pz.restaurantServer.entity.Dish;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
@Transactional
@Repository
public interface DishRepository extends JpaRepository<Dish, Long>{


@Query(value = "Select * from dish d  where d.dish_id = ?1", nativeQuery = true)
public Dish getDishId(Long dishIdv2);


@Modifying
@Query(value = "update dish d set d.dish_id = ?1 where d.dish_id = ?2", nativeQuery = true)
public void setDishId(Long dishIdv2,Dish dishId);

}