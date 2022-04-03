package com.project.stockexchangeappbackend.DTO;
 import lombok.*;
import java.math.BigDecimal;
import java.util.List;
import com.project.stockexchangeappbackend.DTO.Tag;
import com.project.stockexchangeappbackend.DTO.Order;
import com.project.stockexchangeappbackend.DTO.Resource;
import com.project.stockexchangeappbackend.DTO.Role;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

 private  Long id;

 private  String firstName;

 private  String lastName;

 private  String email;

 private  String password;

 private  Role role;

 private  BigDecimal money;

 private  Boolean isActive;

 private  List<Order> orders;

 private  List<Resource> userStocks;

 private  Tag tag;

 private  Long version;


}