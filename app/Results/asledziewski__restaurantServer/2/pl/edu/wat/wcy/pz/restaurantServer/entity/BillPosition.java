import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import javax.persistence;
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

@Column(name = dishIdv2)
 private Long dishIdv2;

@Transient
 private DishRequest dishrequest = new DishRequestImpl();;


public Dish getDishId(){
  this.dishId = dishrequestimpl.getDishId(this.dishIdv2);
return this.dishId;
}


public Long getBillId(){
    return billId;
}


public void setBillId(Long billId){
    this.billId = billId;
}


public void setDishId(Dish dishId){
 dishrequestimpl.setDishId(dishId,this.dishIdv2);



public Long getBillPositionId(){
    return billPositionId;
}


public void setBillPositionId(Long billPositionId){
    this.billPositionId = billPositionId;
}


}