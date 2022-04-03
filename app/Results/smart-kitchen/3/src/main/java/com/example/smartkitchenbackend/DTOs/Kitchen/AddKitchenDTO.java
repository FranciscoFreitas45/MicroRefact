package com.example.smartkitchenbackend.DTOs.Kitchen;
 import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AddKitchenDTO {

 private  long userId;

 private  long kitchenId;


}