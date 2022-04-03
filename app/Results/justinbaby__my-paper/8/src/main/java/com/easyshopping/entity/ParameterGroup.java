package com.easyshopping.entity;
 import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import com.fasterxml.jackson.annotation.JsonProperty;
@Entity
@Table(name = "xx_parameter_group")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "xx_parameter_group_sequence")
public class ParameterGroup extends OrderEntity{

 private  long serialVersionUID;

 private  String name;

 private  ProductCategory productCategory;

 private  List<Parameter> parameters;


public void setName(String name){
    this.name = name;
}


public void setParameters(List<Parameter> parameters){
    this.parameters = parameters;
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


@NotNull
@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(nullable = false)
public ProductCategory getProductCategory(){
    return productCategory;
}


@JsonProperty
@Valid
@NotEmpty
@OneToMany(mappedBy = "parameterGroup", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
@OrderBy("order asc")
public List<Parameter> getParameters(){
    return parameters;
}


}