package com.ushahidi.swiftriver.core.model;
 import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import com.ushahidi.swiftriver.core.Interface.FormField;
@MappedSuperclass
public class DropFormField {

@Id
@GeneratedValue
 protected  Long id;

@ManyToOne
@JoinColumn(name = "droplet_form_id")
 protected  T dropForm;

@ManyToOne
 protected  FormField field;

 protected  String value;


public void setDropForm(T dropForm){
    this.dropForm = dropForm;
}


public String getValue(){
    return value;
}


public T getDropForm(){
    return dropForm;
}


public void setField(FormField field){
    this.field = field;
}


public void setValue(String value){
    this.value = value;
}


public FormField getField(){
    return field;
}


public void setId(Long id){
    this.id = id;
}


public Long getId(){
    return id;
}


}