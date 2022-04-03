package com.easyshopping.entity;
 import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
@Entity
@Table(name = "xx_specification_value")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "xx_specification_val_sequence")
public class SpecificationValue extends OrderEntity{

 private  long serialVersionUID;

 private  String name;

 private  String image;

 private  Specification specification;

 private  Set<Product> products;


public void setName(String name){
    this.name = name;
}


public void setSpecification(Specification specification){
    this.specification = specification;
}


@NotEmpty
@Length(max = 200)
@Column(nullable = false)
public String getName(){
    return name;
}


@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(nullable = false)
public Specification getSpecification(){
    return specification;
}


public void setProducts(Set<Product> products){
    this.products = products;
}


@ManyToMany(mappedBy = "specificationValues", fetch = FetchType.LAZY)
public Set<Product> getProducts(){
    return products;
}


@Length(max = 200)
public String getImage(){
    return image;
}


public void setImage(String image){
    this.image = image;
}


}