package com.easyshopping.DTO;
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
public class SpecificationValue extends OrderEntity{

 private  long serialVersionUID;

 private  String name;

 private  String image;

 private  Specification specification;

 private  Set<Product> products;


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


@ManyToMany(mappedBy = "specificationValues", fetch = FetchType.LAZY)
public Set<Product> getProducts(){
    return products;
}


@Length(max = 200)
public String getImage(){
    return image;
}


}