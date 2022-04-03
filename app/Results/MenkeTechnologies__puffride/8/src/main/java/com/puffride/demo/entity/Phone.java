package com.puffride.demo.entity;
 import lombok;
import java.io.Serializable;
import javax.persistence;
import java.time.LocalDate;
import java.time.LocalDateTime;
@Entity
@Table(name = "PHONE")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Phone implements Serializable{

 private  long serialVersionUID;

@Id
@Column(name = "PHONE_ID")
 private  Integer phoneId;

@Column(name = "COUNTRY_CODE")
 private  Integer countryCode;

@Column(name = "AREA_CODE")
 private  Integer areaCode;

@Column(name = "DIGITS")
 private  Integer digits;

@Column(name = "CREATE_DATE")
 private  LocalDate createDate;

@Column(name = "UPDATE_DATE")
 private  LocalDateTime updateDate;


}