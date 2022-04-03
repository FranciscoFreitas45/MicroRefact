package com.example.demo.DTO;
 import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;
public class OrderDetail implements Serializable{

 private  long serialVersionUID;

 private Long id;

 private Double price;

 private Integer quantity;

 private Product product;

 private Order order;

 private Integer id;


}