package pl.edu.wat.wcy.pz.restaurantServer.DTO;
 import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import pl.edu.wat.wcy.pz.restaurantServer.DTO.Dish;

public class BillPosition {


 private  Long billPositionId;

 private  Dish dishId;

 private  Long billId;





public Dish getDishId(){
return this.dishId;
}


public Long getBillId(){
    return billId;
}


public void setBillId(Long billId){
    this.billId = billId;
}




public Long getBillPositionId(){
    return billPositionId;
}


public void setBillPositionId(Long billPositionId){
    this.billPositionId = billPositionId;
}


}