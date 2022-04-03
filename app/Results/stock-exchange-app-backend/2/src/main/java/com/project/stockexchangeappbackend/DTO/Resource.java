package com.project.stockexchangeappbackend.DTO;
 import lombok.*;
import com.project.stockexchangeappbackend.DTO.Stock;
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Resource {

 private  Long id;

 private  User user;

 private  Stock stock;

 private  Integer amount;


}