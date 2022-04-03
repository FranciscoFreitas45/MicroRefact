package pl.edu.wat.wcy.pz.restaurantServer.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import pl.edu.wat.wcy.pz.restaurantServer.entity.Dish;
@RestController
@CrossOrigin
public class DishBillPositionController {

@Autowired
 private DishBillPositionService dishbillpositionservice;


@GetMapping
("/BillPosition/{id}/Dish/getDishId")
public Dish getDishId(@PathVariable(name="id") Long dishIdv2){
    System.out.println("PEDIDO FEITO");
    System.out.println(dishIdv2); 
return dishbillpositionservice.getDishId(dishIdv2);
}


@PutMapping
("/BillPosition/{id}/Dish/setDishId")
public void setDishId(@PathVariable(name="id") Long dishIdv2,@RequestBody Dish dishId){
dishbillpositionservice.setDishId(dishIdv2,dishId);
}


}