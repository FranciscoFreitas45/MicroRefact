package com.example.demo.entity;
 import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Report implements Serializable{

 private  long serialVersionUID;

@Id
 private  Category loai;

 private  double doanhthu;

 private  long soluong;


}