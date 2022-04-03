package com.project.stockexchangeappbackend.DTO;
 import lombok.*;
import java.math.BigDecimal;
import java.util.List;

import com.project.stockexchangeappbackend.DTO.Resource;

import com.project.stockexchangeappbackend.DTO.Tag;
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Stock {

 private  Long id;

 private  String name;

 private  String abbreviation;

 private  BigDecimal currentPrice;

 private  Integer amount;

 private  Double priceChangeRatio;

 private  List<Resource> resources;

 private  Boolean isDeleted;

 private  Tag tag;


}