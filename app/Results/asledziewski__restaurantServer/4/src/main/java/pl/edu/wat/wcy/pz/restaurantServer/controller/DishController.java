package pl.edu.wat.wcy.pz.restaurantServer.controller;
 import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation;
import pl.edu.wat.wcy.pz.restaurantServer.entity.Dish;
import pl.edu.wat.wcy.pz.restaurantServer.service.DishService;
import java.util.Collection;
import java.util.Optional;
@AllArgsConstructor
@RestController
@CrossOrigin
public class DishController {

 private  DishService dishService;


@DeleteMapping("/dishes/{id}")
public void deleteDish(Long id){
    Optional<Dish> dish = dishService.getDishById(id);
    if (!dish.isPresent())
        throw new RuntimeException("Dish not found");
    dishService.deleteDishById(id);
}


@GetMapping(value = "/dishes/{id}")
public Dish getDishById(Long id){
    Optional<Dish> dish = dishService.getDishById(id);
    return dish.orElse(null);
}


@PutMapping("/dishes/{id}")
public void updateDish(Long id,Dish dish){
    dishService.updateDish(id, dish);
}


@GetMapping("/dishes")
public Collection<Dish> getDishes(){
    return dishService.getDishes();
}


@PostMapping("/dishes")
public void addDish(Dish dish){
    dishService.addDish(dish);
}


@GetMapping(value = "/dishes/name={term}")
public Collection<Dish> findDishes(String term){
    return dishService.findDishes(term);
}


}