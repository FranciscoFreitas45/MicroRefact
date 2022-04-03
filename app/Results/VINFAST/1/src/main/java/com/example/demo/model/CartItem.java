package com.example.demo.model;
 import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartItem {

 private  Integer productid;

 private  String name;

 private  double price;

 private  int qty;


}