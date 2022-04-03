package com.cg.oms.vo;
 import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import com.cg.oms.DTO.Medicine;
import com.cg.oms.DTO.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import lombok.Data;
//import com.cg.oms.Interface.User;
@Data
public class CartVo implements Serializable{

 private  Long serialVersionUID;

@JsonProperty(value = "cartId", access = Access.READ_ONLY)
 private  Long cartId;

 private  List<Medicine> medicineList;

 private  User user;

@JsonProperty(value = "quantity")
 private  Integer quantity;

@JsonProperty(value = "costPerPrice")
 private  Float costPerPiece;


public Long getCartId(){
    return cartId;
}


public List<Medicine> getMedicineList(){
    return medicineList;
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
    return user;
}


public Float getCostPerPiece(){
    return costPerPiece;
}


public void setMedicineList(List<Medicine> medicineList){
    this.medicineList = medicineList;
}


public void setUser(User user){
    this.user = user;
}


public void setCostPerPiece(Float costPerPiece){
    this.costPerPiece = costPerPiece;
}


}