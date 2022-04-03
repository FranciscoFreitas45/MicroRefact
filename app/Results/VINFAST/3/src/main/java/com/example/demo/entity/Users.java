package com.example.demo.entity;
 import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.example.demo.Request.OrderRequest;
import com.example.demo.Request.Impl.OrderRequestImpl;
import com.example.demo.DTO.Order;
@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Users implements Serializable{

 private  long serialVersionUID;

@Id
@NotNull(message = "Không được để trống username")
 private String username;

@NotNull(message = "Không được để trống password")
 private String password;

@NotNull(message = "Không được để trống fullname")
 private String fullname;

@NotNull(message = "Không được để trống Email")
@Email(message = "Sai định đạng Email")
 private String email;

@NotNull(message = "Không được để trống ảnh")
 private String photo;

 private boolean activated;

 private boolean admin;

@Transient
 private List<Order> order;

@Transient
 private OrderRequest orderrequest = new OrderRequestImpl();;


}