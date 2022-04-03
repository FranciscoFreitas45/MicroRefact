package com.example.demo.entity;
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
import com.example.demo.Request.OrderDetailRequest;
import com.example.demo.Request.Impl.OrderDetailRequestImpl;
import com.example.demo.DTO.OrderDetail;
@Data
@Entity
@Table(name = "products")
@NoArgsConstructor
@AllArgsConstructor
public class Product implements Serializable{

 private  long serialVersionUID;

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@NotNull
 private Integer id;

@NotNull
 private String name;

 private String images;

@NotNull
@Min(0)
 private Double price;

@Temporal(TemporalType.DATE)
 private Date createDate;

 private Boolean available;

@ManyToOne
@JoinColumn(name = "categoryid")
 private Category category;

@Transient
 private List<OrderDetail> orderDetails;

@Transient
 private OrderDetailRequest orderdetailrequest = new OrderDetailRequestImpl();;


}