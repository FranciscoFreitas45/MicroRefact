package com.example.demo.DTO;
 import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
public class Product implements Serializable{

 private  long serialVersionUID;

 private Integer id;

 private String name;

 private String images;

 private Double price;

 private Date createDate;

 private Boolean available;

 private Category category;

 private List<OrderDetail> orderDetails;


}