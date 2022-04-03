package com.ushahidi.swiftriver.core.model;
 import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import com.ushahidi.swiftriver.core.Interface.Form;
@MappedSuperclass
public class DropForm {

@Id
@GeneratedValue
 protected  Long id;

@ManyToOne
@JoinColumn(name = "drop_id")
 protected  T drop;

@ManyToOne
 protected  Form form;

@OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER, mappedBy = "dropForm")
 protected  List<U> values;


public List<U> getValues(){
    return values;
}


public Form getForm(){
    return form;
}


public void setValues(List<U> values){
    this.values = values;
}


public void setDrop(T drop){
    this.drop = drop;
}


public void setId(Long id){
    this.id = id;
}


public Long getId(){
    return id;
}


public T getDrop(){
    return drop;
}


public void setForm(Form form){
    this.form = form;
}


}