package com.example.demo.entity;
 import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data
@Table(name = "categories")
@NoArgsConstructor
@AllArgsConstructor
public class Category implements Serializable{

 private  long serialVersionUID;

@Id
 private String id;

 private String name;

@OneToMany(mappedBy = "category")
 private List<Product> products;


}