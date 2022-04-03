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
  public Dish getDishId(@PathVariable(name="id") Long dishId0IRY){
  return dishbillpositionservice.getDishId(dishId0IRY);
   }


  @PutMapping
  ("/BillPosition/{id}/Dish/setDishId")
  public void setDishId(@PathVariable(name="id") Long dishId0IRY,@RequestBody Dish dishId){
  dishbillpositionservice.setDishId(dishId0IRY,dishId);
   }
  }



