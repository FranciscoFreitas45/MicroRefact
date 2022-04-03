package com.ec.survey.model.survey;
 import javax.persistence.Cacheable;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
@Entity
@DiscriminatorValue("TRANSLATION")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class TranslationsElement extends Element{

 private  long serialVersionUID;

public TranslationsElement() {
}
public TranslationsElement copy(String fileDir){
    return new TranslationsElement();
}


@Override
public boolean differsFrom(Element element){
    return false;
}


}