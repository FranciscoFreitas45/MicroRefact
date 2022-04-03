package com.cg.oms.DTO;
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
import com.cg.oms.Request.MedicineRequest;
import com.cg.oms.Request.Impl.MedicineRequestImpl;
import com.cg.oms.DTO.Medicine;
import com.cg.oms.Request.UserRequest;
import com.cg.oms.Request.Impl.UserRequestImpl;
import com.cg.oms.DTO.User;
public class Cart {

 private  Long cartId;

 private  List<Medicine> medicineList;

 private  User user;

 private  Integer quantity;

 private  Float costPerPiece;

 private Long userId;

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


public User getUser(){
  this.user = userrequest.getUser(this.userId);
return this.user;
}


public Float getCostPerPiece(){
    return costPerPiece;
}


}