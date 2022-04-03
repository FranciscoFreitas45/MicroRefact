package com.easyshopping.DTO;
 import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
public class Specification extends OrderEntity{

 private  long serialVersionUID;

 private  String name;

 private  Type type;

 private  String memo;

 private  List<SpecificationValue> specificationValues;

 private  Set<Product> products;


public void setName(String name){
    this.name = name;
}


public void setMemo(String memo){
    this.memo = memo;
}


@Length(max = 200)
public String getMemo(){
    return memo;
}


@NotEmpty
@Length(max = 200)
@Column(nullable = false)
public String getName(){
    return name;
}


public void setSpecificationValues(List<SpecificationValue> specificationValues){
    this.specificationValues = specificationValues;
}


@NotNull
@Column(nullable = false)
public Type getType(){
    return type;
}


public void setProducts(Set<Product> products){
    this.products = products;
}


public void setType(Type type){
    this.type = type;
}


@ManyToMany(mappedBy = "specifications", fetch = FetchType.LAZY)
public Set<Product> getProducts(){
    return products;
}


@Valid
@NotEmpty
@OneToMany(mappedBy = "specification", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
@OrderBy("order asc")
public List<SpecificationValue> getSpecificationValues(){
    return specificationValues;
}


}