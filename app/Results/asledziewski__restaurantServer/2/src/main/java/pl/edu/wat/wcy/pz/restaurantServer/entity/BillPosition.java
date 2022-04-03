package pl.edu.wat.wcy.pz.restaurantServer.entity;
 import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import javax.persistence;
 import javax.persistence.*;

 import org.hibernate.annotations.Entity;
 import pl.edu.wat.wcy.pz.restaurantServer.Request.DishRequest;
import pl.edu.wat.wcy.pz.restaurantServer.Request.Impl.DishRequestImpl;
import pl.edu.wat.wcy.pz.restaurantServer.DTO.Dish;
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class BillPosition {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "BILL_POSITION_ID")
         private  Long billPositionId;

        @Transient
         private  Dish dishId;

        @Column(name = "BILL_ID")
         private  Long billId;

        @Column(name = "dishId0IRY")
         private Long dishId0IRY;

        @Transient
         private final DishRequest dishrequest = new DishRequestImpl();


public Dish getDishId(){
  this.dishId = dishrequest.getDishId(this.dishId0IRY);
return this.dishId;
}}



public Long getBillId(){
    return billId;
}


public void setBillId(Long billId){
    this.billId = billId;
}


public void setDishId(Dish dishId){
this.dishId0IRY = dishId.getDishid() ;
dishrequest.setDishId(dishId,this.dishId0IRY);
 this.dishId = dishId;
}



public Long getBillPositionId(){
    return billPositionId;
}


public void setBillPositionId(Long billPositionId){
    this.billPositionId = billPositionId;
}


}