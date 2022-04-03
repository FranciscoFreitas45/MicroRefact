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
import lombok.Data;
import com.example.demo.Request.UsersRequest;
import com.example.demo.Request.Impl.UsersRequestImpl;
import com.example.demo.DTO.Users;
public class Order implements Serializable{

 private  long serialVersionUID;

 private Long id;

 private Date cteateDate;

 private String address;

 private Integer status;

 private Users users;

 private List<OrderDetail> orderDetails;


}