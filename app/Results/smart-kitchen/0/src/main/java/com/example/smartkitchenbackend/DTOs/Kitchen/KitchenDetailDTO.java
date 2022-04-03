package com.example.smartkitchenbackend.DTOs.Kitchen;
 import com.example.smartkitchenbackend.DTOs.ingredient.IngredientDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class KitchenDetailDTO {

 private  long id;

 private  String name;

 private  List<IngredientDTO> ingredients;

 private  long wishListId;

 private  List<Long> userIds;


}