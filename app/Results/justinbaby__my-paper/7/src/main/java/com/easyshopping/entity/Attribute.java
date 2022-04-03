package com.easyshopping.entity;
 import java.util.ArrayList;
import java.util.List;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import com.fasterxml.jackson.annotation.JsonProperty;
@Entity
@Table(name = "xx_attribute")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "xx_attribute_sequence")
public class Attribute extends OrderEntity{

 private  long serialVersionUID;

 private  String name;

 private  Integer propertyIndex;

 private  ProductCategory productCategory;

 private  List<String> options;


public void setName(String name){
    this.name = name;
}


public void setPropertyIndex(Integer propertyIndex){
    this.propertyIndex = propertyIndex;
}


public void setProductCategory(ProductCategory productCategory){
    this.productCategory = productCategory;
}


@JsonProperty
@NotEmpty
@Length(max = 200)
@Column(nullable = false)
public String getName(){
    return name;
}


@Column(nullable = false, updatable = false)
public Integer getPropertyIndex(){
    return propertyIndex;
}


@NotNull(groups = Save.class)
@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(nullable = false, updatable = false)
public ProductCategory getProductCategory(){
    return productCategory;
}


public void setOptions(List<String> options){
    this.options = options;
}


@JsonProperty
@NotEmpty
@ElementCollection
@CollectionTable(name = "xx_attribute_option")
public List<String> getOptions(){
    return options;
}


}