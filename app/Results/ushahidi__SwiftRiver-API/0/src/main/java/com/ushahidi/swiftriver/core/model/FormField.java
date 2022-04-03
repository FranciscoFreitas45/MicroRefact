package com.ushahidi.swiftriver.core.model;
 import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
@Entity
@Table(name = "form_fields")
public class FormField {

@Id
@GeneratedValue
 private  Long id;

@ManyToOne
@JoinColumn(name = "form_id")
 private  Form form;

 private  String title;

 private  String description;

 private  String type;

 private  Boolean required;

 private  String options;


public void setRequired(Boolean required){
    this.required = required;
}


public Form getForm(){
    return form;
}


public void setTitle(String title){
    this.title = title;
}


public Long getId(){
    return id;
}


public void setDescription(String description){
    this.description = description;
}


public void setType(String type){
    this.type = type;
}


public void setForm(Form form){
    this.form = form;
}


public String getDescription(){
    return description;
}


public String getTitle(){
    return title;
}


public String getType(){
    return type;
}


@Override
public int hashCode(){
    return new HashCodeBuilder(17, 31).append(title).append(type).toHashCode();
}


@Override
public boolean equals(Object obj){
    if (obj == null)
        return false;
    if (obj == this)
        return true;
    if (obj.getClass() != getClass())
        return false;
    FormField other = (FormField) obj;
    return new EqualsBuilder().append(title, other.title).append(type, other.type).isEquals();
}


public void setId(Long id){
    this.id = id;
}


public void setOptions(String options){
    this.options = options;
}


@Override
public String toString(){
    return "FormField [id=" + id + ", form=" + form + ", title=" + title + ", description=" + description + ", type=" + type + ", required=" + required + ", options=" + options + "]";
}


public String getOptions(){
    return options;
}


public Boolean getRequired(){
    return required;
}


}