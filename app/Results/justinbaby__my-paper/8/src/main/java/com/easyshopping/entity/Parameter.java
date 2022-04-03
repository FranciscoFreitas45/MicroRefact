package com.easyshopping.entity;
 import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import com.fasterxml.jackson.annotation.JsonProperty;
@Entity
@Table(name = "xx_parameter")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "xx_parameter_sequence")
public class Parameter extends OrderEntity{

 private  long serialVersionUID;

 private  String name;

 private  ParameterGroup parameterGroup;


public void setName(String name){
    this.name = name;
}


public void setParameterGroup(ParameterGroup parameterGroup){
    this.parameterGroup = parameterGroup;
}


@JsonProperty
@NotEmpty
@Length(max = 200)
@Column(nullable = false)
public String getName(){
    return name;
}


@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(nullable = false)
public ParameterGroup getParameterGroup(){
    return parameterGroup;
}


}