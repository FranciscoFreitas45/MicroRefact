package com.example.smartkitchenbackend.entities;
 import lombok.Data;
import javax.persistence;
import java.util.ArrayList;
import java.util.List;
import com.example.smartkitchenbackend.Request.WishedIngredientRequest;
import com.example.smartkitchenbackend.Request.Impl.WishedIngredientRequestImpl;
import com.example.smartkitchenbackend.DTO.WishedIngredient;
import com.example.smartkitchenbackend.Request.KitchenRequest;
import com.example.smartkitchenbackend.Request.Impl.KitchenRequestImpl;
import com.example.smartkitchenbackend.DTO.Kitchen;
@Data
@Entity
public class WishList {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
 private  long id;

@Transient
 private  List<WishedIngredient> ingredients;

@Transient
 private  Kitchen kitchen;

@Transient
 private WishedIngredientRequest wishedingredientrequest = new WishedIngredientRequestImpl();;

@Column(name = "id")
 private long id;

@Transient
 private KitchenRequest kitchenrequest = new KitchenRequestImpl();;


}