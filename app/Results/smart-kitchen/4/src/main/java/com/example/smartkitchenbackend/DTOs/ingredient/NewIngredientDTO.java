package com.example.smartkitchenbackend.DTOs.ingredient;
 import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class NewIngredientDTO {

 private  String name;

 private  double weightOrCount;

 private  String type;


}