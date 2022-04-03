package com.example.demo.entity;
 import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;
import com.example.demo.Request.ProductRequest;
import com.example.demo.Request.Impl.ProductRequestImpl;
import com.example.demo.DTO.Product;
@Data
@Entity
@Table(name = "orders_detail")
public class OrderDetail implements Serializable{

 private  long serialVersionUID;

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long id;

 private Double price;

 private Integer quantity;

@Transient
 private Product product;

@ManyToOne
@JoinColumn(name = "orderid")
 private Order order;

@Column(name = "id")
 private Integer id;

@Transient
 private ProductRequest productrequest = new ProductRequestImpl();;


}