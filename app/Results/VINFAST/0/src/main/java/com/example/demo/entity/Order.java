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
import lombok.Data;
import com.example.demo.Request.UsersRequest;
import com.example.demo.Request.Impl.UsersRequestImpl;
import com.example.demo.DTO.Users;
@Data
@Entity
@Table(name = "orders")
public class Order implements Serializable{

 private  long serialVersionUID;

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long id;

@Temporal(TemporalType.DATE)
 private Date cteateDate;

 private String address;

 private Integer status;

@Transient
 private Users users;

@OneToMany(mappedBy = "order")
 private List<OrderDetail> orderDetails;

@Column(name = "username")
 private String username;

@Transient
 private UsersRequest usersrequest = new UsersRequestImpl();;


}