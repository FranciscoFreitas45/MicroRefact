package com.softserve.edu.Resources.entity;
 import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
@Embeddable
public class ConstrainedProperty {

@ManyToOne
@JoinColumn(name = "Id_Property", nullable = false, updatable = false)
 private ResourceProperty property;

@NotNull
@Column(name = "Searchable", nullable = false)
 private  boolean searchable;

@NotNull
@Column(name = "Required", nullable = false)
 private  boolean required;

@NotNull
@Column(name = "Uniq", nullable = false)
 private  boolean unique;

public ConstrainedProperty() {
}public ConstrainedProperty(ResourceProperty property) {
    this.property = property;
}
public boolean isRequired(){
    return required;
}


public ConstrainedProperty setUnique(boolean unique){
    this.unique = unique;
    return this;
}


public ConstrainedProperty setRequired(boolean required){
    this.required = required;
    return this;
}


public ResourceProperty getProperty(){
    return property;
}


public ConstrainedProperty setSearchable(boolean searchable){
    this.searchable = searchable;
    return this;
}


public ConstrainedProperty setProperty(ResourceProperty property){
    this.property = property;
    return this;
}


public boolean isUnique(){
    return unique;
}


public boolean isSearchable(){
    return searchable;
}


}