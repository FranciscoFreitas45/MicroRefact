package pl.edu.wat.wcy.pz.restaurantServer.entity;
 import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import pl.edu.wat.wcy.pz.restaurantServer.Request.DishRequest;
import pl.edu.wat.wcy.pz.restaurantServer.Request.Impl.DishRequestImpl;
import pl.edu.wat.wcy.pz.restaurantServer.DTO.Dish;
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class BillPosition {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "BILL_POSITION_ID")
 private  Long billPositionId;

@Transient
 private  Dish dishId;

@Column(name = "BILL_ID")
 private  Long billId;

@Column(name = "dishIdv2")
 private Long dishIdv2;

@Transient
 private DishRequest dishrequest = new DishRequestImpl();;

public BillPosition(long billPositionId,Dish d, long billId){
    this.billPositionId = billPositionId;
    this.dishId = d;
    this.dishIdv2 = d.getDishId();
    this.billId = billId;
}


public Dish getDishId(){
    
  this.dishId = dishrequest.getDishId(this.dishIdv2);
return this.dishId;
}


public Long getBillId(){
    return billId;
}


public void setBillId(Long billId){
    this.billId = billId;
}


public void setDishId(Dish dishId){
 dishrequest.setDishId(dishId,this.dishIdv2);
}



public Long getBillPositionId(){
    return billPositionId;
}


public void setBillPositionId(Long billPositionId){
    this.billPositionId = billPositionId;
}


}