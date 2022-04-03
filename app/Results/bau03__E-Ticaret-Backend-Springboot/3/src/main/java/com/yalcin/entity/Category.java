package com.yalcin.entity;
 import javax.persistence;
@Entity
@Table(name = "cloud_category", schema = "public")
public class Category {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
 private  Integer id;

@Enumerated(EnumType.STRING)
@Column(name = "category", length = 60)
 private  Categorys categorys;


public void setCategorys(Categorys categorys){
    this.categorys = categorys;
}


public void setId(Integer id){
    this.id = id;
}


public Integer getId(){
    return id;
}


public Categorys getCategorys(){
    return categorys;
}


}