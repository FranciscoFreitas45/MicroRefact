package com.example.smartkitchenbackend.DTOs.Food;
 import com.example.smartkitchenbackend.DTOs.ingredient.IngredientDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class FoodDetailDTO {

 private  String name;

 private  long id;

 private  List<IngredientDTO> ingredients;


}