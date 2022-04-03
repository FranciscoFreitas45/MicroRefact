package com.example.smartkitchenbackend.entities;
 import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import javax.persistence;
import com.example.smartkitchenbackend.Request.KitchenRequest;
import com.example.smartkitchenbackend.Request.Impl.KitchenRequestImpl;
import com.example.smartkitchenbackend.DTO.Kitchen;
@Entity
@Data
public class IngredientInKitchen {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
 private  long id;

 private  double weightOrCount;

@ManyToOne
 private  Ingredient ingredient;

@Transient
 private  Kitchen kitchen;

@Column(name = "id")
 private long id;

@Transient
 private KitchenRequest kitchenrequest = new KitchenRequestImpl();;


}