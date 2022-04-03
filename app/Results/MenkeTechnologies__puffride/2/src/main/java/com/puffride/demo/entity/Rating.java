package com.puffride.demo.entity;
 import lombok;
import java.io.Serializable;
import javax.persistence;
import java.time.LocalDate;
import java.time.LocalDateTime;
import com.puffride.demo.Request.RideRequest;
import com.puffride.demo.Request.Impl.RideRequestImpl;
import com.puffride.demo.DTO.Ride;
@Entity
@Table(name = "RATING")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Rating implements Serializable{

 private  long serialVersionUID;

@Id
@Column(name = "RATING_ID")
 private  Integer rating;

@Transient
 private  Ride ride;

@Column(name = "AMOUNT")
 private  Integer amount;

@Column(name = "CREATE_DATE")
 private  LocalDate createDate;

@Column(name = "UPDATE_DATE")
 private  LocalDateTime updateDate;

@Column(name = "ridev2")
 private Integer ridev2;

@Transient
 private RideRequest riderequest = new RideRequestImpl();;


}