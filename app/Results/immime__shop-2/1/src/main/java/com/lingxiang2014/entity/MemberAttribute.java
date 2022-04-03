package com.lingxiang2014.entity;
 import java.util.ArrayList;
import java.util.List;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
@Entity
@Table(name = "lx_member_attribute")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "lx_member_attribute_sequence")
public class MemberAttribute extends OrderEntity{

 private  long serialVersionUID;

 private  String name;

 private  Type type;

 private  Boolean isEnabled;

 private  Boolean isRequired;

 private  Integer propertyIndex;

 private  List<String> options;


public void setName(String name){
    this.name = name;
}


public void setPropertyIndex(Integer propertyIndex){
    this.propertyIndex = propertyIndex;
}


@NotEmpty
@Length(max = 200)
@Column(nullable = false)
public String getName(){
    return name;
}


@Column(updatable = false)
public Integer getPropertyIndex(){
    return propertyIndex;
}


@NotNull(groups = Save.class)
@Column(nullable = false, updatable = false)
public Type getType(){
    return type;
}


@NotNull
@Column(nullable = false)
public Boolean getIsRequired(){
    return isRequired;
}


public void setIsRequired(Boolean isRequired){
    this.isRequired = isRequired;
}


public void setIsEnabled(Boolean isEnabled){
    this.isEnabled = isEnabled;
}


public void setOptions(List<String> options){
    this.options = options;
}


@ElementCollection
@CollectionTable(name = "lx_member_attribute_option")
public List<String> getOptions(){
    return options;
}


public void setType(Type type){
    this.type = type;
}


@NotNull
@Column(nullable = false)
public Boolean getIsEnabled(){
    return isEnabled;
}


}