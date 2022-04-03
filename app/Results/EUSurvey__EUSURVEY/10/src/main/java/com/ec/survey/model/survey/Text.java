package com.ec.survey.model.survey;
 import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.owasp.esapi.errors.ValidationException;
import javax.persistence.Cacheable;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
@Entity
@DiscriminatorValue("TEXT")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Text extends Question{

 private  long serialVersionUID;

public Text(String text, String uid) {
    setTitle(text);
    setUniqueId(uid);
}public Text() {
}
public Text copy(String fileDir){
    Text copy = new Text();
    baseCopy(copy);
    return copy;
}


@Override
public boolean differsFrom(Element element){
    return basicDiffersFrom(element);
}


}