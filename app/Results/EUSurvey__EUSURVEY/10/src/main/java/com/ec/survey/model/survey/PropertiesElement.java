package com.ec.survey.model.survey;
 import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import javax.persistence.Cacheable;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
@Entity
@DiscriminatorValue("PROPERTY")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class PropertiesElement extends Element{

 private  boolean orderChanged;

 private  long serialVersionUID;

public PropertiesElement() {
}public PropertiesElement(boolean order) {
    orderChanged = order;
}
public boolean isOrderChanged(){
    return orderChanged;
}


public PropertiesElement copy(String fileDir){
    PropertiesElement copy = new PropertiesElement();
    copy.orderChanged = orderChanged;
    return copy;
}


public void setOrderChanged(boolean orderChanged){
    this.orderChanged = orderChanged;
}


@Override
public boolean differsFrom(Element element){
    return false;
}


}