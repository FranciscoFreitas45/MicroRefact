package com.example.smartkitchenbackend.DTOs.ingredient;
 import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class IngredientDTO {

 private  long id;

 private  String name;

 private  double weightOrCount;

 private  String type;


}