package com.puffride.demo.entity;
 import lombok;
import java.io.Serializable;
import javax.persistence;
import java.time.LocalDate;
import java.time.LocalDateTime;
import com.puffride.demo.Request.UserRequest;
import com.puffride.demo.Request.Impl.UserRequestImpl;
import com.puffride.demo.DTO.User;
@Entity
@Table(name = "RIDER")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Rider implements Serializable{

 private  long serialVersionUID;

@Id
@Column(name = "RIDER_ID")
 private  Integer rider;

@Column(name = "DRIVER_VERIFIED_FLAG")
 private  String driverVerifiedFlag;

@Transient
 private  User user;

@Column(name = "CREATE_DATE")
 private  LocalDate createDate;

@Column(name = "UPDATE_DATE")
 private  LocalDateTime updateDate;

@Column(name = "userv2")
 private Integer userv2;

@Transient
 private UserRequest userrequest = new UserRequestImpl();;


}