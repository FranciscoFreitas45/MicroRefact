import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
@AllArgsConstructor
@RestController
@CrossOrigin
public class DishBillPositionController {

 private DishBillPositionService dishbillpositionservice;


@GetMapping
("/BillPosition/{id}/Dish/getDishId")
public Dish getDishId(@PathVariable(name="id") Long dishIdv2){
dishbillpositionservice.getDishId(dishIdv2);
}


@PutMapping
("/BillPosition/{id}/Dish/setDishId")
public void setDishId(@PathVariable(name="id") Long dishIdv2,@RequestBody Dish dishId){
dishbillpositionservice.setDishId(dishIdv2,dishId);
}


}