package com.puffride.demo.DTO;
 import lombok;
import java.io.Serializable;
import javax.persistence;
import java.time.LocalDate;
import java.time.LocalDateTime;
public class Phone implements Serializable{

 private  long serialVersionUID;

 private  Integer phoneId;

 private  Integer countryCode;

 private  Integer areaCode;

 private  Integer digits;

 private  LocalDate createDate;

 private  LocalDateTime updateDate;


}