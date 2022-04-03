package com.softserve.edu.Resources.DTO;
 import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
public class ConstrainedProperty {

 private ResourceProperty property;

 private  boolean searchable;

 private  boolean required;

 private  boolean unique;

public ConstrainedProperty() {
}public ConstrainedProperty(ResourceProperty property) {
    this.property = property;
}
public ResourceProperty getProperty(){
    return property;
}


}