package com.example.smartkitchenbackend.DTOs.Kitchen;
 import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class NewKitchenDTO {

 private  String name;

 private  long userId;


}