package com.example.demo.DTO;
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
public class Users implements Serializable{

 private  long serialVersionUID;

 private String username;

 private String password;

 private String fullname;

 private String email;

 private String photo;

 private boolean activated;

 private boolean admin;

 private List<Order> order;


}