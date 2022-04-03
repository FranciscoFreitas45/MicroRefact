package com.project.stockexchangeappbackend.DTO;
 import lombok;
import javax.persistence;
import java.math.BigDecimal;
import java.util.List;
import com.project.stockexchangeappbackend.Request.TagRequest;
import com.project.stockexchangeappbackend.Request.Impl.TagRequestImpl;
import com.project.stockexchangeappbackend.DTO.Tag;
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