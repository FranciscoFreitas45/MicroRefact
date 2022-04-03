package com.cg.oms.model;
 import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import org.springframework.stereotype.Component;
import com.cg.oms.Request.OrderRequest;
import com.cg.oms.Request.Impl.OrderRequestImpl;
import com.cg.oms.DTO.Order;
import com.cg.oms.Request.MedicineRequest;
import com.cg.oms.Request.Impl.MedicineRequestImpl;
import com.cg.oms.DTO.Medicine;
@Entity
@Component
public class OrderMedicine {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
 private  Long orderMedicineId;

@Transient
 private  Order order;

@Transient
 private  List<Medicine> medicineList;

 private  Integer quantity;

 private  Double price;

@Column(name = "orderId")
 private Long orderId;

@Transient
 private OrderRequest orderrequest = new OrderRequestImpl();;

@Transient
 private MedicineRequest medicinerequest = new MedicineRequestImpl();;

public OrderMedicine() {
    super();
}
public void setOrderMedicineId(Long orderMedicineId){
    this.orderMedicineId = orderMedicineId;
}


public void setOrder(Order order){
 orderrequest.setOrder(order,this.orderId);
}



public List<Medicine> getMedicineList(){
  this.medicineList = medicinerequest.getMedicineList(this.orderMedicineId);
return this.medicineList;
}


public Integer getQuantity(){
    return quantity;
}


public void setQuantity(Integer quantity){
    this.quantity = quantity;
}


public Long getOrderMedicineId(){
    return orderMedicineId;
}


public Order getOrder(){
  this.order = orderrequest.getOrder(this.orderId);
return this.order;
}


@Override
public String toString(){
    return "OrderMedicine [orderMedicineId=" + orderMedicineId + ", order=" + order + ", medicineList=" + medicineList + ", quantity=" + quantity + ", price=" + price + "]";
}


public void setMedicineList(List<Medicine> medicineList){
 medicinerequest.setMedicineList(medicineList,this.orderMedicineId);
}



public void setPrice(Double price){
    this.price = price;
}


public Double getPrice(){
    return price;
}


}