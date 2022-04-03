package com.cg.oms.model;
 import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.*;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import org.springframework.stereotype.Component;
import com.cg.oms.Request.MedicineRequest;
import com.cg.oms.Request.Impl.MedicineRequestImpl;
import com.cg.oms.DTO.Medicine;
import com.cg.oms.Request.UserRequest;
import com.cg.oms.Request.Impl.UserRequestImpl;
import com.cg.oms.DTO.User;
@Entity
@Component
public class Cart {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
 private  Long cartId;

@Transient
 private  List<Medicine> medicineList;

@Transient
 private  User user;

 private  Integer quantity;

 private  Float costPerPiece;

@Transient
 private MedicineRequest medicinerequest = new MedicineRequestImpl();;

@Column(name = "userId")
 private Long userId;

@Transient
 private UserRequest userrequest = new UserRequestImpl();;

/**
 * creating parameterized and default constructor
 */
public Cart() {
    super();
}
public Long getCartId(){
    return cartId;
}


public List<Medicine> getMedicineList(){
  this.medicineList = medicinerequest.getMedicineList(this.cartId);
return this.medicineList;
}


public Integer getQuantity(){
    return quantity;
}


public void setCartId(Long cartId){
    this.cartId = cartId;
}


public void setQuantity(Integer quantity){
    this.quantity = quantity;
}


public User getUser(){
  this.user = userrequest.getUser(this.userId);
return this.user;
}


public Float getCostPerPiece(){
    return costPerPiece;
}


@Override
public String toString(){
    return "Cart [cartId=" + cartId + ", medicineList=" + medicineList + ", user=" + user + ", quantity=" + quantity + ", costPerPiece=" + costPerPiece + "]";
}


public void setMedicineList(List<Medicine> medicineList){
 medicinerequest.setMedicineList(medicineList,this.cartId);
}



public void setUser(User user){
 userrequest.setUser(user,this.userId);
}



public void setCostPerPiece(Float costPerPiece){
    this.costPerPiece = costPerPiece;
}


}